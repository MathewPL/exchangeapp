package com.mkrasniews.exchangeapp.exchange.api

import com.mkrasniews.exchangeapp.account.model.Currency.PLN
import com.mkrasniews.exchangeapp.account.model.Currency.USD
import com.mkrasniews.exchangeapp.exchange.model.ExchangeRate
import com.mkrasniews.exchangeapp.exchange.repository.ExchangeRateRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.math.BigDecimal.ONE
import java.math.RoundingMode.CEILING

@Component
class ExchangeRateUpdater(
        private val exchangeRatesFetcher: ExchangeRatesFetcher,
        private val exchangeRateRepository: ExchangeRateRepository
) {

    //Updating rate every 60 seconds
    @Scheduled(fixedRate = 60000)
    fun updateRates() {
        exchangeRatesFetcher.fetchActual()?.let {
            val rate = it.rates[0]
            val exchangeRateUsdPln = ExchangeRate(
                    rate.effectiveDate,
                    USD,
                    PLN,
                    rate.mid
            )
            val exchangeRatePlnUsd = ExchangeRate(
                    rate.effectiveDate,
                    PLN,
                    USD,
                    ONE.divide(rate.mid, 4, CEILING)
            )

            exchangeRateRepository.save(exchangeRateUsdPln)
            exchangeRateRepository.save(exchangeRatePlnUsd)
        }
    }
}