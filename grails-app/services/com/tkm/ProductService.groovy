package com.tkm

import com.tkm.Product
import com.tkm.SearchContext
import com.tkm.SearchableField

class ProductService {

    final static fcn = [
        'like': 'like',
        'ilike': 'ilike',
        '=': 'eq',
        '!=': 'ne',
        '<=': 'le',
        '>=': 'ge',
        '<': 'lt',
        '>': 'gt',
        'isNull': 'isNull'
    ]

    static decorator = [
        'like': { '%' + it + '%' },
        'ilike': { '%' + it + '%' },
        '=': { it },
        '!=': { it },
        '<=': { it },
        '>=': { it },
        '<': { it },
        '>': { it },
        'isNull': { it },
        'isNotNull': { it }
    ]

    def search(SearchContext searchContext) {
        def rsp = [:]
        try {
            def criteria = Product.createCriteria()

            def resultList = criteria.list(max: searchContext.max ?: null, offset: searchContext.offset) {

                searchContext.fields.each {
                    def value = it.value
                    def name = it.name
                    def operator = it.operator

                    "${fcn[operator]}"(name, decorator[operator](value))
                }
                if (searchContext.sort != null && !searchContext.sort.isEmpty()) {
                    order(searchContext.sort, searchContext.order)
                }
                ne("status", EntityStatus.DELETED)
            }

            rsp.results = resultList
        }
        catch (Exception ex) {
            log.error("searchProducts() failed: ${ex.message}", ex)
            rsp.errors = ex.message
        }
        return rsp
    }

    def save(Product product) {
        def rsp = [:]
        try {
            product.save(flush: true, failOnError: true)
        }
        catch (Exception ex) {
            log.error("save() failed: ${ex.message}", ex)
            rsp.errors = ex.message
        }
        return rsp
    }

    def getProductById(Long id) {
        def rsp = [:]
        try {
            def product = Product.withCriteria {
                eq('id', id)
                ne('status', EntityStatus.DELETED)
            }[0]

            rsp.result = product
        }
        catch (Exception ex) {
            log.error("getProductById() failed: ${ex.message}", ex)
            rsp.errors = ex.message
        }
        return rsp
    }

    def update(Product updatedProduct) {
        def rsp = [:]
        try {
            def product = Product.withCriteria {
                eq('id', updatedProduct.id)
                ne('status', EntityStatus.DELETED)
            }[0]

            product.name = updatedProduct.name
            product.description = updatedProduct.description
            product.save(flush: true, failOnError: true)

            rsp.result = product
        }
        catch (Exception ex) {
            log.error("getProductById() failed: ${ex.message}", ex)
            rsp.errors = ex.message
        }
        return rsp
    }

    def delete(List<Long> ids) {
        def rsp = [:]
        try {
            def products = Product.withCriteria {
                inList('id', ids)
                ne('status', EntityStatus.DELETED)
            }

            products.each { product ->
                product.status = EntityStatus.DELETED
                product.save(flush: true, failOnError: true)
            }

            rsp.results = products
        }
        catch (Exception ex) {
            log.error("delete() failed: ${ex.message}", ex)
            rsp.errors = ex.message
        }
        return rsp
    }
}
