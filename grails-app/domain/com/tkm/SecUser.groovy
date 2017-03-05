package com.tkm

class SecUser implements Serializable {

    transient springSecurityService

    UserProfile userProfile

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
