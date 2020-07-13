package com.mkrasniews.exchangeapp.commons

import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DateProviderImpl :DateProvider {

    override fun now(): LocalDate {
        return LocalDate.now()
    }
}