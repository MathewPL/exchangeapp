package com.mkrasniews.exchangeapp.user.factory

import com.mkrasniews.exchangeapp.account.model.Currency.PLN
import com.mkrasniews.exchangeapp.account.model.Currency.USD
import com.mkrasniews.exchangeapp.account.model.CurrencyAccount
import com.mkrasniews.exchangeapp.api.exception.InvalidUserDataException
import com.mkrasniews.exchangeapp.user.model.Pesel
import com.mkrasniews.exchangeapp.user.model.User
import java.math.BigDecimal
import java.math.BigDecimal.ZERO

class UserFactory {
    companion object {
        fun createUser(name: String, surname: String, pesel: String, plnBalance: BigDecimal): User {
            val pesel = Pesel(pesel)
            if (!pesel.isValid()) {
                throw(InvalidUserDataException("Invalid PESEL"))
            }
            val accounts = listOf(
                    CurrencyAccount(PLN, plnBalance, mutableListOf()),
                    CurrencyAccount(USD, ZERO, mutableListOf())
            )
            val user = User(name, surname, pesel)
            user.addCurrencyAccounts(accounts)

            return user
        }
    }
}