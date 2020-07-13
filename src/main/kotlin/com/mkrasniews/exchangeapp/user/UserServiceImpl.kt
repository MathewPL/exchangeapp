package com.mkrasniews.exchangeapp.user

import com.mkrasniews.exchangeapp.api.exception.UserNotFoundException
import com.mkrasniews.exchangeapp.user.model.User
import com.mkrasniews.exchangeapp.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun findUser(idUser: Long): User {
        userRepository.findByIdOrNull(idUser)?.let {
            return it
        } ?: throw UserNotFoundException()
    }

}