package com.mkrasniews.exchangeapp.exchange

import com.mkrasniews.exchangeapp.account.model.Currency
import com.mkrasniews.exchangeapp.commons.Money
import com.mkrasniews.exchangeapp.exchange.model.Exchange
import com.mkrasniews.exchangeapp.user.model.User
import org.springframework.stereotype.Service
import java.math.RoundingMode.*
import java.time.LocalDate

@Service
class ExchangeServiceImpl(private val exchangeRateService: ExchangeRateService) : ExchangeService {

    override fun exchangeMoney(money: Money, to: Currency, evalDate: LocalDate, user: User): Exchange {
        val exchangeRate = exchangeRateService.exchangeRate(money.currency, to, evalDate)

        val exchangedValue = exchangeRate.rate.multiply(money.value).setScale(4, CEILING)

        return Exchange(money, Money(to, exchangedValue), evalDate, exchangeRate, user)
    }
}