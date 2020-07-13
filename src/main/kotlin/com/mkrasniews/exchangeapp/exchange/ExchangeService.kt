package com.mkrasniews.exchangeapp.exchange

import com.mkrasniews.exchangeapp.account.model.Currency
import com.mkrasniews.exchangeapp.commons.Money
import com.mkrasniews.exchangeapp.exchange.model.Exchange
import com.mkrasniews.exchangeapp.user.model.User
import java.time.LocalDate

interface ExchangeService {
    fun exchangeMoney(money: Money, to: Currency, evalDate: LocalDate, user: User): Exchange
}