package com.tkm

import com.tkm.IEntity
import com.tkm.EntityStatus
import com.tkm.PendingStatus

class Transaction implements Serializable, IEntity {

    Long id
    SecUser user
    Cart cart
    BigDecimal totalAmount

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
    }

    static constraints = {
        user()
        cart()
        totalAmount(size: 1..100)

        // IEntity
        status()
        pendingStatus(nullable: true)
        dateCreated()
        lastUpdated()
        createdBy(size: 1..50)
        updatedBy(size: 1..50)
    }
}