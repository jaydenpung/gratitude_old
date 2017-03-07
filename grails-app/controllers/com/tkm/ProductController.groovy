package com.tkm

import grails.converters.JSON

import com.tkm.Product
import com.tkm.SearchContext

class ProductController {

    def productService

    def list() {
        try {
        }
        catch (Exception ex) {
            log.error("list() failed: ${ex.message}", ex)
        }
    }

    def search() {
        try {
            def searchContext = new SearchContext()
            [
                [ name: "name", value: params.name, operator: "ilike" ]
            ].each {
                if (it.value) {
                    def searchableField = new SearchableField()
                    searchableField.name = it.name
                    searchableField.value = it.value
                    searchableField.operator = it.operator
                    searchContext.fields.add(searchableField)
                }
            }

            def draw = params.draw
            def offset = params.int('start')
            def maxResults = params.length

            def orderDirection = params.find { it.key ==~ /order\[\d+\]\[dir\]/ }
            def orderColumn = params.find { it.key ==~ /order\[\d+\]\[column\]/ }
            def orderColumnName = params.find { it.key ==~ /columns\[${orderColumn.value}]\[name\]/ }

            searchContext.order = orderDirection.value
            searchContext.sort = orderColumnName.value
            searchContext.max = maxResults?.toInteger()
            searchContext.offset = offset?.toInteger()

            def rsp = productService.search(searchContext)

            def rows = []

            if (rsp) {
                rsp.results.each { product ->
                    rows << [
                        id: product.id,
                        name: product.name,
                        description: product.description
                    ]
                }
            }

            def result = [
                draw: params.draw,
                data: rows,
                recordsTotal: rsp.results.totalCount,
                recordsFiltered: rsp.results.totalCount
            ]

            render(result as JSON)
        }
        catch(Exception ex) {
            log.error("search() failed: ${ex.message}", ex)
        }
    }

    def create() {
        try {
        }
        catch (Exception ex) {
            log.error("create() failed: ${ex.message}", ex)
        }
    }

    def save() {
        boolean status = false
        def errorMessage

        try {
            def product = new Product(
                name: params.name,
                description: params.description
            )

            def rsp = productService.save(product)

            if (rsp.errors) {
                errorMessage = rsp.errors
            }
            else {
                status = true
            }
        }
        catch (Exception ex) {
            log.error("save() failed: ${ex.message}", ex)
            errorMessage = ex.message
        }

        def result = [
            status: status,
            errorMessage: errorMessage
        ]

        render(result as JSON)
    }

    def edit(Long id) {
        try {
            def rsp = productService.getProductById(id)
            def product = rsp.result

            [ product: product ]
        }
        catch (Exception ex) {
            log.error("edit() failed: ${ex.message}", ex)
        }
    }

    def update() {
        boolean status = false
        def errorMessage

        try {
            def product = new Product(
                name: params.name,
                description: params.description
            )
            product.id = params.long('id')

            def rsp = productService.update(product)

            if (rsp.errors) {
                errorMessage = rsp.errors
            }
            else {
                status = true
            }

            [ product: product ]
        }
        catch (Exception ex) {
            log.error("update() failed: ${ex.message}", ex)
            errorMessage = ex.message
        }

        def result = [
            status: status,
            errorMessage: errorMessage
        ]

        render(result as JSON)
    }

    def delete() {
        boolean status = false
        def errorMessage

        try {
            def ids = params.list('id')*.toLong()

            def rsp = productService.delete(ids)

            if (rsp.errors) {
                errorMessage = rsp.errors
            }
            else {
                status = true
            }
        }
        catch (Exception ex) {
            log.error("delete() failed: ${ex.message}", ex)
            status = false
            errorMessage = ex.message
        }

        def result = [
            status: status,
            errorMessage: errorMessage
        ]

        render(result as JSON)
    }
}
