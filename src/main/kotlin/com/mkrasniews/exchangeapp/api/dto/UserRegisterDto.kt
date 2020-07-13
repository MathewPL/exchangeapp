package com.mkrasniews.exchangeapp.api.dto

import java.math.BigDecimal

data class UserRegisterDto(
        val name: String,
        val surname: String,
        val pesel: String,
        val plnBalance: BigDecimal
)