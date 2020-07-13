package com.mkrasniews.exchangeapp.exchange.api

import java.math.BigDecimal
import java.time.LocalDate

class NbpCurrencyRate(val code: String, val rates: List<NbpRate>)

class NbpRate(val mid: BigDecimal, val effectiveDate: LocalDate)