package com.mkrasniews.exchangeapp.api.service

import com.mkrasniews.exchangeapp.account.model.Currency
import com.mkrasniews.exchangeapp.api.dto.UserDetailsDto
import com.mkrasniews.exchangeapp.user.model.User
import java.math.BigDecimal

interface UserApiService {

    fun transferMoney(fromCurrency: Currency, toCurrency: Currency, value: BigDecimal, user: User)
    fun userDetails(user: User): UserDetailsDto
    fun createAccount(name: String, surname: String, pesel: String, plnBalance: BigDecimal): User
}