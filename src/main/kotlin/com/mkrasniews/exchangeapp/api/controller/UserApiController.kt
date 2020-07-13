package com.mkrasniews.exchangeapp.api.controller

import com.mkrasniews.exchangeapp.api.dto.ExchangeDto
import com.mkrasniews.exchangeapp.api.dto.UserDetailsDto
import com.mkrasniews.exchangeapp.api.dto.UserRegisterDto
import com.mkrasniews.exchangeapp.api.service.UserApiService
import com.mkrasniews.exchangeapp.user.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/user")
class UserApiController(
        private val userApiService: UserApiService,
        private val userService: UserService
) {

    @ResponseBody
    @RequestMapping("/createUser")
    fun createUser(@RequestBody userRegisterDto: UserRegisterDto): Long {
        return userApiService.createAccount(
                userRegisterDto.name,
                userRegisterDto.surname,
                userRegisterDto.pesel,
                userRegisterDto.plnBalance
        ).id
    }

    @ResponseBody
    @RequestMapping("/userDetails/{idUser}")
    fun userDetails(@PathVariable idUser: Long): UserDetailsDto {
        return userApiService.userDetails(userService.findUser(idUser))
    }

    @ResponseBody
    @RequestMapping("/exchange")
    fun exchange(@RequestBody exchangeDto: ExchangeDto) {
        return userApiService.transferMoney(
                exchangeDto.from,
                exchangeDto.to,
                exchangeDto.value,
                userService.findUser(exchangeDto.idUser))
    }
}