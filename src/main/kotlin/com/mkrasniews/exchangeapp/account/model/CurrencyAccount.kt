package com.mkrasniews.exchangeapp.account.model

import com.mkrasniews.exchangeapp.api.exception.InsufficientBalanceException
import com.mkrasniews.exchangeapp.commons.Money
import com.mkrasniews.exchangeapp.commons.model.BaseEntity
import java.math.BigDecimal
import javax.persistence.*
import javax.persistence.EnumType.STRING

@Entity
class CurrencyAccount(
        @Enumerated(STRING) val currency: Currency,
        var balance: BigDecimal,
        @OneToMany(cascade = [CascadeType.ALL]) val history: MutableList<AccountTransaction>
) : BaseEntity() {

    fun withdraw(value: BigDecimal): Money {
        if(balance < value) {
            throw InsufficientBalanceException()
        }
        balance -= value
        history.add(AccountTransaction(-value))

        return Money(currency, value)
    }

    fun deposit(value: BigDecimal) {
        balance += value
        history.add(AccountTransaction(value))
    }
}