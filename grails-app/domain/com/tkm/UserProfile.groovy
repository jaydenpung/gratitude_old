package com.tkm

import com.tkm.IEntity
import com.tkm.EntityStatus
import com.tkm.PendingStatus

class UserProfile extends UserProfileBase implements Serializable, IEntity {

    Long id
    UserProfileTmp tmp

    // IEntity
    EntityStatus status = EntityStatus.ACTIVE
    PendingStatus pendingStatus
    Date dateCreated
    Date lastUpdated
    String createdBy = '_SYSTEM_'
    String updatedBy = '_SYSTEM_'

    static embedded = ['tmp']

    static mapping = {
        table 'USER_PROFILE'
        id generator:'sequence', params: [sequence: 'USER_PROFILE_SEQ']
        tmp lazy: false
    }

    static constraints = {
        tmp(nullable: true)

        // IEntity
        status()
        pendingStatus(nullable: true)
        dateCreated()
        lastUpdated()
        createdBy(size: 1..50)
        updatedBy(size: 1..50)
    }
}
