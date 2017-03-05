package com.tkm

abstract class SecUserBase implements Serializable, Comparable<SecUserBase> {

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    static constraints = {
        username blank: false, unique: true
        password blank: false
    }

    @Override
    public int compareTo(SecUserBase other) {
        username <=> other.username
    }
}
