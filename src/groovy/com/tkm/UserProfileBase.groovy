package com.tkm

abstract class UserProfileBase implements Serializable, Comparable<UserProfileBase> {
    String name
    String email
    String address
    String phoneNo
    String password

    static constraints = {
        name(size: 1..100)
        email(size: 1..100)
        address(size: 1..200)
        phoneNo(size: 1..20)
        password(size: 1..20)
    }

    @Override
    public int compareTo(UserProfileBase other) {
        email <=> other.email
    }
}