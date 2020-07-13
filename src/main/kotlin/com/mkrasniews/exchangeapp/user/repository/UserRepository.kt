package com.mkrasniews.exchangeapp.user.repository

import com.mkrasniews.exchangeapp.user.model.User
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepository: JpaRepository<User, Long>