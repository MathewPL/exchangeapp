package com.mkrasniews.exchangeapp.api.exception

import org.springframework.http.HttpStatus.*
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(NOT_FOUND)
class UserNotFoundException : RuntimeException()