package com.mkrasniews.exchangeapp.commons

import java.time.LocalDate

interface DateProvider {
    fun now(): LocalDate
}