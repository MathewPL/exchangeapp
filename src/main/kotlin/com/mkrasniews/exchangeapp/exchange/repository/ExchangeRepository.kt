package com.mkrasniews.exchangeapp.exchange.repository

import com.mkrasniews.exchangeapp.exchange.model.Exchange
import org.springframework.data.jpa.repository.JpaRepository


interface ExchangeRepository: JpaRepository<Exchange, Long>