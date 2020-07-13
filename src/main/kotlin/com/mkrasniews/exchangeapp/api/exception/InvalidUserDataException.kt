package com.mkrasniews.exchangeapp.api.exception

import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(INTERNAL_SERVER_ERROR)
class InvalidUserDataException(message: String) : RuntimeException(message)