package com.mkrasniews.exchangeapp.exchange.api

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import java.time.Duration

private const val ROOT_URI = "http://api.nbp.pl/api/exchangerates"

@Service
class ExchangeRatesFetcher {

    fun fetchActual(): NbpCurrencyRate? {
        val uri = "/rates/a/usd/last/1/?format=json"

        return RestTemplateBuilder()
                .rootUri(ROOT_URI)
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(5))
                .build()
                .getForObject(uri, NbpCurrencyRate::class.java)
    }
}