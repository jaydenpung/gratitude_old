package com.tkm

class SecUser extends SecUserBase implements Serializable {

    transient springSecurityService

    UserProfile userProfile
    SecUserTmp tmp

    static embedded = ['tmp']

    static transients = ['springSecurityService']

    static constraints = {
        userProfile (nullable: false)
    }

    Set<Role> getAuthorities() {
        SecUserRole.findAllBySecUser(this).collect { it.role }
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }
}
