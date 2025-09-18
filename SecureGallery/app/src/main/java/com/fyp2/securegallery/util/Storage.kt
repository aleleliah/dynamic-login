package com.fyp2.securegallery.util

import com.fyp2.securegallery.model.User

class Storage {

    private val PIN = "PIN"
    private val EXIST = "EXIST"

    fun savePin(pin: String){
        Stash.put(PIN, pin)
    }

    fun getPin(): String{
        return Stash.getString(PIN, "")
    }

    fun saveExist(exist: Boolean){
        Stash.put(EXIST, exist)
    }

    fun getExist(): Boolean{
        return Stash.getBoolean(EXIST, false)
    }

    fun saveUser(user: User){
        Stash.put("User", user)
    }

    fun getUser(): User {
        return Stash.getObject<User>("User", User::class.java) as User
    }
}