package com.mkrasniews.exchangeapp.user.model

import com.mkrasniews.exchangeapp.account.model.Currency
import com.mkrasniews.exchangeapp.account.model.CurrencyAccount
import com.mkrasniews.exchangeapp.commons.model.BaseEntity
import java.time.LocalDate
import java.time.Period
import javax.persistence.*

private const val ADULT_USER_AGE = 18

@Entity
class User(val name: String, val surname: String, @Embedded val pesel: Pesel) : BaseEntity() {
    @OneToMany(cascade = [CascadeType.ALL])
    private val accounts: MutableList<CurrencyAccount> = mutableListOf()

    fun addCurrencyAccounts(currencyAccounts: List<CurrencyAccount>) = accounts.addAll(currencyAccounts)

    fun getCurrencyAccount(currency: Currency) = accounts.first { it.currency == currency }

    fun isAdult(currentDate: LocalDate): Boolean {
        return pesel.age(currentDate) >= ADULT_USER_AGE
    }
}

@Embeddable
class Pesel(@Column(unique = true) private val pesel: String) {

    fun isValid(): Boolean {
        return pesel.length == 11
    }

    fun age(currentDate: LocalDate): Int {
        val monthPart = pesel.substring(2, 4).toInt()
        val yearPart = pesel.substring(0, 2).toInt()

        val century = if (monthPart > 20) 20 else 19
        val year = century * 100 + yearPart
        val month = if (century == 20) monthPart - 20 else monthPart
        val day = pesel.substring(4, 6).toInt()

        val dob = LocalDate.of(year, month, day)

        return Period.between(dob, currentDate).years
    }
}