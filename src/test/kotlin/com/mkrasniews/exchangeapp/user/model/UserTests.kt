package com.mkrasniews.exchangeapp.user.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import com.mkrasniews.exchangeapp.user.factory.UserFactory
import java.math.BigDecimal
import java.time.LocalDate

@RunWith(MockitoJUnitRunner::class)
internal class UserTests {

    @Test
    fun userShouldBeAdult() {
        val user = UserFactory.createUser("Test", "Test", "02210115587", BigDecimal(100))

        val result = user.isAdult(LocalDate.of(2020, 1,1))

        assertThat(result).isEqualTo(true)
    }

    @Test
    fun userShouldNotBeAdult() {
        val user = UserFactory.createUser("Test", "Test", "02210263518", BigDecimal(100))

        val result = user.isAdult(LocalDate.of(2020, 1,1))

        assertThat(result).isEqualTo(false)
    }
}