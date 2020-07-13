package com.mkrasniews.exchangeapp.api.service

import com.mkrasniews.exchangeapp.account.model.Currency
import com.mkrasniews.exchangeapp.account.model.Currency.*
import com.mkrasniews.exchangeapp.api.exception.UserNotFoundException
import com.mkrasniews.exchangeapp.api.dto.UserDetailsDto
import com.mkrasniews.exchangeapp.api.exception.InvalidUserDataException
import com.mkrasniews.exchangeapp.commons.DateProvider
import com.mkrasniews.exchangeapp.exchange.ExchangeService
import com.mkrasniews.exchangeapp.exchange.repository.ExchangeRepository
import com.mkrasniews.exchangeapp.user.factory.UserFactory
import com.mkrasniews.exchangeapp.user.model.User
import org.springframework.stereotype.Service
import com.mkrasniews.exchangeapp.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import java.math.BigDecimal

@Service
class UserApiServiceImpl(
        private val exchangeRepository: ExchangeRepository,
        private val userRepository: UserRepository,
        private val exchangeService: ExchangeService,
        private val dateProvider: DateProvider
) : UserApiService {

    override fun createAccount(name: String, surname: String, pesel: String, plnBalance: BigDecimal): User {
        val user = UserFactory.createUser(name, surname, pesel, plnBalance)

        if (!user.isAdult(dateProvider.now())) {
            throw(InvalidUserDataException("User is not adult"))
        }

        return userRepository.save(user)
    }

    override fun transferMoney(fromCurrency: Currency, toCurrency: Currency, value: BigDecimal, user: User) {
        val evalDate = dateProvider.now()

        val accountFrom = user.getCurrencyAccount(fromCurrency)
        val accountTo = user.getCurrencyAccount(toCurrency)

        val money = accountFrom.withdraw(value)
        val exchange = exchangeService.exchangeMoney(money, toCurrency, evalDate, user)
        accountTo.deposit(exchange.to.value)

        userRepository.save(user)
        exchangeRepository.save(exchange)
    }

    override fun userDetails(user: User): UserDetailsDto {
        val plnAccount = user.getCurrencyAccount(PLN)
        val usdAccount = user.getCurrencyAccount(USD)

        return UserDetailsDto(user.name, user.surname, plnAccount.balance, usdAccount.balance)
    }
}