package com.tkm

class SecUser implements Serializable {

    transient springSecurityService

    UserProfile userProfile
    SortedSet<Transaction> transactions = new TreeSet<Transaction>()

    static transients = ['springSecurityService']

    static constraints = {
        userProfile(nullable: false)
        transactions(nullable: true)
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
