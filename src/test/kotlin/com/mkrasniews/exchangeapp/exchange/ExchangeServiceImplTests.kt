package com.mkrasniews.exchangeapp.exchange

import com.mkrasniews.exchangeapp.account.model.Currency.PLN
import com.mkrasniews.exchangeapp.account.model.Currency.USD
import com.mkrasniews.exchangeapp.commons.Money
import com.mkrasniews.exchangeapp.exchange.model.ExchangeRate
import com.mkrasniews.exchangeapp.user.factory.UserFactory
import org.assertj.core.api.SoftAssertions
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import java.math.BigDecimal
import java.math.BigDecimal.TEN
import java.math.RoundingMode
import java.time.LocalDate

@RunWith(MockitoJUnitRunner::class)
internal class ExchangeServiceImplTests {

    @Mock
    private lateinit var exchangeRateService: ExchangeRateService

    @InjectMocks
    private lateinit var exchangeService: ExchangeServiceImpl

    @Test
    fun shouldExchangeCorrectly() {
        val evalDate = LocalDate.of(2020, 1, 1)
        val money = Money(PLN, BigDecimal(2))
        val currencyTo = USD

        given(exchangeRateService.exchangeRate(PLN, USD, evalDate))
            .willReturn(ExchangeRate(evalDate, PLN, USD, BigDecimal(5)))

        val result = exchangeService.exchangeMoney(
                money,
                currencyTo,
                evalDate,
                UserFactory.createUser("TEST", "TEST", "11111111111", BigDecimal(1000))
        )

        val softly = SoftAssertions()

        softly.assertThat(result.from).isEqualToComparingFieldByField(money)
        softly.assertThat(result.to.currency).isEqualTo(currencyTo)
        softly.assertThat(result.from.value).isEqualByComparingTo(BigDecimal(2))
        softly.assertThat(result.to.value).isEqualByComparingTo(TEN)
        softly.assertThat(result.date).isEqualTo(evalDate)

        softly.assertAll()
    }
}