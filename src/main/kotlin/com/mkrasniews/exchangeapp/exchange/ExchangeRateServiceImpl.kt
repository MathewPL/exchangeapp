package com.mkrasniews.exchangeapp.exchange

import com.mkrasniews.exchangeapp.account.model.Currency
import com.mkrasniews.exchangeapp.exchange.model.ExchangeRate
import com.mkrasniews.exchangeapp.exchange.repository.ExchangeRateRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ExchangeRateServiceImpl(
        private val exchangeRateRepository: ExchangeRateRepository
) : ExchangeRateService {

    override fun exchangeRate(currency: Currency, destCurrency: Currency, date: LocalDate): ExchangeRate {
        return exchangeRateRepository.findTopByCurrencyAndDestCurrencyOrderByIdDesc(currency, destCurrency)
    }
}