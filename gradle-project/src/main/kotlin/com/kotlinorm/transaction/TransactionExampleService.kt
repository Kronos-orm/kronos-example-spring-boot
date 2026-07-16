package com.kotlinorm.transaction

import com.kotlinorm.Kronos
import com.kotlinorm.orm.insert.insert
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TransactionExampleService {

    @Transactional
    fun createAccount(accountId: Int) {
        insertAccount(accountId)
        insertAudit(accountId)
    }

    @Transactional
    fun createAccountThenFail(accountId: Int): Nothing {
        insertAccount(accountId)
        insertAudit(accountId)
        error("force Spring transaction rollback")
    }

    @Transactional
    fun createInNestedKronosTransactionThenFail(accountId: Int): Nothing {
        Kronos.transact {
            insertAccount(accountId)
            insertAudit(accountId)
        }
        error("the outer Spring transaction still owns the commit")
    }

    fun createInKronosTransaction(accountId: Int) {
        Kronos.transact {
            insertAccount(accountId)
            insertAudit(accountId)
        }
    }

    fun createInKronosTransactionThenFail(accountId: Int): Nothing {
        Kronos.transact {
            insertAccount(accountId)
            insertAudit(accountId)
            error("force standalone Kronos transaction rollback")
        }
        error("unreachable")
    }

    private fun insertAccount(accountId: Int) {
        TransactionAccount(accountId, "account-$accountId").insert().execute()
    }

    private fun insertAudit(accountId: Int) {
        TransactionAudit(accountId, accountId, "created").insert().execute()
    }
}
