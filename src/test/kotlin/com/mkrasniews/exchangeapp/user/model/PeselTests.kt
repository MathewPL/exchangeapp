package com.mkrasniews.exchangeapp.user.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import java.time.LocalDate

@RunWith(MockitoJUnitRunner::class)
internal class PeselTests {

    @Test
    fun peselShouldBeValid() {
        val pesel = Pesel("02210191921")

        val result = pesel.isValid()

        assertThat(result).isEqualTo(true)
    }

    @Test
    fun peselShouldBeInvalid() {
        val pesel = Pesel("0221019192")

        val result = pesel.isValid()

        assertThat(result).isEqualTo(false)
    }

    @Test
    fun peselShouldReturn23Age() {
        val pesel = Pesel("03210144742")

        val result = pesel.age(LocalDate.of(2020, 1, 1))

        assertThat(result).isEqualTo(17)
    }

    @Test
    fun peselShouldReturn17Age() {
        val pesel = Pesel("96070172635")

        val result = pesel.age(LocalDate.of(2020, 1, 1))

        assertThat(result).isEqualTo(23)
    }
}