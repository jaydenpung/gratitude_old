package com.tkm

import com.tkm.IEntity
import com.tkm.EntityStatus
import com.tkm.PendingStatus

class Product implements Serializable, IEntity {

    Long id
    String name
    String description1
    String description2

    // IEntity
    EntityStatus status = EntityStatus.ACTIVE
    PendingStatus pendingStatus
    Date dateCreated
    Date lastUpdated
    String createdBy = '_SYSTEM_'
    String updatedBy = '_SYSTEM_'

    static mapping = {
        table 'PRODUCT'
        id generator:'sequence', params: [sequence: 'PRODUCT_SEQ']
    }

    static constraints = {
        name(size: 1..100)
        description1(size: 1..200)
        description2(size: 1..200)

        // IEntity
        status()
        pendingStatus(nullable: true)
        dateCreated()
        lastUpdated()
        createdBy(size: 1..50)
        updatedBy(size: 1..50)
    }
}