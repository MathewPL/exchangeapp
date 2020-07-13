package com.mkrasniews.exchangeapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ExchangeappApplication

fun main(args: Array<String>) {
	runApplication<ExchangeappApplication>(*args)
}
