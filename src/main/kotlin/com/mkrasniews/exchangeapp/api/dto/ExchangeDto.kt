package com.mkrasniews.exchangeapp.api.dto

import com.mkrasniews.exchangeapp.account.model.Currency
import java.math.BigDecimal

data class ExchangeDto(
        val from: Currency,
        val to: Currency,
        val idUser: Long,
        val value: BigDecimal
)