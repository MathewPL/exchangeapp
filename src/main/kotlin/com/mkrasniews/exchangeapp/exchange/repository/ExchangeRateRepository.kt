package com.mkrasniews.exchangeapp.exchange.repository

import com.mkrasniews.exchangeapp.account.model.Currency
import com.mkrasniews.exchangeapp.exchange.model.ExchangeRate
import org.springframework.data.jpa.repository.JpaRepository


interface ExchangeRateRepository: JpaRepository<ExchangeRate, Long> {
    fun findTopByCurrencyAndDestCurrencyOrderByIdDesc(currency: Currency, destCurrency: Currency): ExchangeRate
}