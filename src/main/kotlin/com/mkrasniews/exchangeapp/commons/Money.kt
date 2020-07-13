package com.mkrasniews.exchangeapp.commons

import com.mkrasniews.exchangeapp.account.model.Currency
import com.mkrasniews.exchangeapp.commons.model.BaseEntity
import java.math.BigDecimal
import javax.persistence.Embeddable
import javax.persistence.Entity
import javax.persistence.EnumType.*
import javax.persistence.Enumerated

@Embeddable
class Money(@Enumerated(STRING) val currency: Currency, val value: BigDecimal)