package com.numble.banking.user

import com.numble.banking.friend.dsl.Friends
import com.numble.banking.user.dsl.Users
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class User(id: EntityID<Long>) : LongEntity(id), UserDetails {
    companion object : LongEntityClass<User>(Users)

    var name by Users.name
    var email by Users.email

    @get:JvmName("getPassword1")
    var password by Users.password
    var expired by Users.expired
    var locked by Users.locked

    var friends by User.via(Friends.sender, Friends.receiver)

    override fun getUsername(): String = email
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> =
        mutableListOf(SimpleGrantedAuthority("ROLE_USER"))

    override fun getPassword() = password
    override fun isAccountNonExpired(): Boolean = !expired
    override fun isAccountNonLocked(): Boolean = !locked
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}