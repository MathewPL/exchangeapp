package com.mkrasniews.exchangeapp.exchange.model

import com.mkrasniews.exchangeapp.commons.Money
import com.mkrasniews.exchangeapp.commons.model.BaseEntity
import com.mkrasniews.exchangeapp.user.model.User
import java.time.LocalDate
import javax.persistence.*

@Entity
class Exchange(
        @Embedded
        @AttributeOverrides(
                AttributeOverride(name = "currency", column = Column(name = "fromCurrency")),
                AttributeOverride(name = "value", column = Column(name = "fromValue"))
        )
        val from: Money,
        @Embedded
        @AttributeOverrides(
                AttributeOverride(name = "currency", column = Column(name = "toCurrency")),
                AttributeOverride(name = "value", column = Column(name = "toValue"))
        )
        val to: Money,
        val date: LocalDate,
        @ManyToOne val rate: ExchangeRate,
        @ManyToOne val user: User
) : BaseEntity()