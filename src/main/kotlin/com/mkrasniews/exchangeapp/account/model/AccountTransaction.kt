package com.mkrasniews.exchangeapp.account.model

import com.mkrasniews.exchangeapp.commons.model.BaseEntity
import java.math.BigDecimal
import javax.persistence.Entity

@Entity
class AccountTransaction(val value: BigDecimal) : BaseEntity()