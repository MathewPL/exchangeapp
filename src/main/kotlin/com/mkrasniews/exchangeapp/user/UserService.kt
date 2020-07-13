package com.mkrasniews.exchangeapp.user

import com.mkrasniews.exchangeapp.user.model.User


interface UserService {
    fun findUser(idUser: Long): User
}