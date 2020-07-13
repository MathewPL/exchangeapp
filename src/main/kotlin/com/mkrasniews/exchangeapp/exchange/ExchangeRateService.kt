package com.mkrasniews.exchangeapp.exchange

import com.mkrasniews.exchangeapp.account.model.Currency
import com.mkrasniews.exchangeapp.exchange.model.ExchangeRate
import java.time.LocalDate

interface ExchangeRateService {

    fun exchangeRate(currency: Currency, destCurrency: Currency, date: LocalDate): ExchangeRate
}