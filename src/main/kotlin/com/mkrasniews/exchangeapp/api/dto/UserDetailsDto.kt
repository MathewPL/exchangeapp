package com.mkrasniews.exchangeapp.api.dto

import java.math.BigDecimal

data class UserDetailsDto(
        val name: String,
        val surname: String,
        val plnBalance: BigDecimal,
        val usdBalance: BigDecimal
)