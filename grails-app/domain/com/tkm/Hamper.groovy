package com.tkm

import com.tkm.IEntity
import com.tkm.EntityStatus
import com.tkm.PendingStatus

class Hamper implements Serializable, IEntity {

    Long id
    String name
    String description1
    String description2
    BigDecimal price
    int quantity
    SortedSet<Product> products = new TreeSet<Product>()

    // IEntity
    EntityStatus status = EntityStatus.ACTIVE
    PendingStatus pendingStatus
    Date dateCreated
    Date lastUpdated
    String createdBy = '_SYSTEM_'
    String updatedBy = '_SYSTEM_'

    static hasMany = [
        products: Product,
    ]

    static mapping = {
        table 'HAMPER'
        id generator:'sequence', params: [sequence: 'HAMPER_SEQ']
        products lazy: false
    }

    static constraints = {
        name(size: 1..100)
        description1(size: 1..200)
        description2(size: 1..200)
        price(size: 1..20)
        products(nullable: true)
        quantity(size: 1..20)

        // IEntity
        status()
        pendingStatus(nullable: true)
        dateCreated()
        lastUpdated()
        createdBy(size: 1..50)
        updatedBy(size: 1..50)
    }
}