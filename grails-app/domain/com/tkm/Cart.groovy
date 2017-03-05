package com.tkm

import com.tkm.IEntity
import com.tkm.EntityStatus
import com.tkm.PendingStatus

class Cart implements Serializable, IEntity {

    Long id
    SortedSet<Hamper> hampers = new TreeSet<Hamper>()

    // IEntity
    EntityStatus status = EntityStatus.ACTIVE
    PendingStatus pendingStatus
    Date dateCreated
    Date lastUpdated
    String createdBy = '_SYSTEM_'
    String updatedBy = '_SYSTEM_'

    static mapping = {
        table 'CART'
        id generator:'sequence', params: [sequence: 'CART_SEQ']
        hampers lazy: false
    }

    static constraints = {
        hampers(nullable: true)

        // IEntity
        status()
        pendingStatus(nullable: true)
        dateCreated()
        lastUpdated()
        createdBy(size: 1..50)
        updatedBy(size: 1..50)
    }
}