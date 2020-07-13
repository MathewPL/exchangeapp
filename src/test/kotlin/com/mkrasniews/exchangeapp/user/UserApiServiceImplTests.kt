package com.mkrasniews.exchangeapp.user

import com.mkrasniews.exchangeapp.account.model.Currency.PLN
import com.mkrasniews.exchangeapp.account.model.Currency.USD
import com.mkrasniews.exchangeapp.api.service.UserApiService
import com.mkrasniews.exchangeapp.api.service.UserApiServiceImpl
import com.mkrasniews.exchangeapp.commons.DateProvider
import com.mkrasniews.exchangeapp.exchange.ExchangeRateService
import com.mkrasniews.exchangeapp.exchange.ExchangeService
import com.mkrasniews.exchangeapp.exchange.ExchangeServiceImpl
import com.mkrasniews.exchangeapp.exchange.model.ExchangeRate
import com.mkrasniews.exchangeapp.exchange.repository.ExchangeRepository
import com.mkrasniews.exchangeapp.user.factory.UserFactory
import com.mkrasniews.exchangeapp.user.repository.UserRepository
import org.assertj.core.api.SoftAssertions
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import java.math.BigDecimal
import java.time.LocalDate

@RunWith(MockitoJUnitRunner::class)
internal class UserApiServiceImplTests {

    @Mock
    private lateinit var exchangeRepository: ExchangeRepository

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var exchangeRateService: ExchangeRateService

    @Mock
    private lateinit var dateProvider: DateProvider

    private lateinit var exchangeService: ExchangeService
    private lateinit var userApiService: UserApiService

    @Before
    fun setup() {
        exchangeService = ExchangeServiceImpl(exchangeRateService)
        userApiService = UserApiServiceImpl(exchangeRepository, userRepository, exchangeService, dateProvider)

        given(dateProvider.now()).willReturn(LocalDate.of(2020, 1, 1))

        given(exchangeRateService.exchangeRate(PLN, USD, dateProvider.now()))
            .willReturn(ExchangeRate(LocalDate.of(2020, 1, 1), PLN, USD, BigDecimal("0.3")))
    }

    @Test
    fun shouldExchange() {
        val user = UserFactory.createUser("TEST", "TEST", "11111111111", BigDecimal(1000))

        userApiService.transferMoney(PLN, USD, BigDecimal(300), user)

        val softly = SoftAssertions()

        softly.assertThat(user.getCurrencyAccount(PLN).balance).isEqualByComparingTo(BigDecimal(700))
        softly.assertThat(user.getCurrencyAccount(USD).balance).isEqualByComparingTo(BigDecimal(90))

        softly.assertAll()
    }


    @Test
    fun shouldReturnCorrectAccontDetails() {
        val user = UserFactory.createUser("TEST", "TEST", "11111111111", BigDecimal(1000))

        userApiService.transferMoney(PLN, USD, BigDecimal(300), user)

        val result = userApiService.userDetails(user)

        val softly = SoftAssertions()

        softly.assertThat(result.name).isEqualTo("TEST")
        softly.assertThat(result.surname).isEqualTo("TEST")
        softly.assertThat(result.plnBalance).isEqualByComparingTo(BigDecimal(700))
        softly.assertThat(result.usdBalance).isEqualByComparingTo(BigDecimal(90))

        softly.assertAll()
    }
}