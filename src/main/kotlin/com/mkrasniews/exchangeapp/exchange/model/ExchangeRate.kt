package com.mkrasniews.exchangeapp.exchange.model

import com.mkrasniews.exchangeapp.account.model.Currency
import com.mkrasniews.exchangeapp.commons.model.BaseEntity
import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.EnumType.*
import javax.persistence.Enumerated

@Entity
class ExchangeRate(
        val date: LocalDate,
        @Enumerated(STRING) val currency: Currency,
        @Enumerated(STRING) val destCurrency: Currency,
        @Column(columnDefinition = "DECIMAL(5,4)") val rate: BigDecimal
) : BaseEntity()