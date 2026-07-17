package com.kotlinorm.transaction

import com.kotlinorm.annotations.PrimaryKey
import com.kotlinorm.annotations.Table
import com.kotlinorm.interfaces.KPojo

@Table("transaction_account")
data class TransactionAccount(
    @PrimaryKey
    var id: Int? = null,
    var name: String? = null
) : KPojo

@Table("transaction_audit")
data class TransactionAudit(
    @PrimaryKey
    var id: Int? = null,
    var accountId: Int? = null,
    var action: String? = null
) : KPojo
