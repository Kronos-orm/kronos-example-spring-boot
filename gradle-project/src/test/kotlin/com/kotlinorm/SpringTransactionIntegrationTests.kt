package com.kotlinorm

import com.kotlinorm.orm.ddl.table
import com.kotlinorm.orm.delete.delete
import com.kotlinorm.orm.select.select
import com.kotlinorm.transaction.TransactionAccount
import com.kotlinorm.transaction.TransactionAudit
import com.kotlinorm.transaction.TransactionExampleService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SpringTransactionIntegrationTests @Autowired constructor(
    private val service: TransactionExampleService
) {

    @BeforeAll
    fun createTables() {
        Kronos.dataSource().table.syncTable<TransactionAccount>()
        Kronos.dataSource().table.syncTable<TransactionAudit>()
    }

    @BeforeEach
    fun clearTables() {
        TransactionAudit().delete().logic(false).execute()
        TransactionAccount().delete().logic(false).execute()
    }

    @Test
    fun `Spring transaction commits both Kronos writes`() {
        service.createAccount(1)

        assertRows(accountCount = 1, auditCount = 1)
    }

    @Test
    fun `Spring transaction rolls back both Kronos writes`() {
        assertThrows(IllegalStateException::class.java) {
            service.createAccountThenFail(2)
        }

        assertRows(accountCount = 0, auditCount = 0)
    }

    @Test
    fun `nested Kronos transaction does not commit before Spring`() {
        assertThrows(IllegalStateException::class.java) {
            service.createInNestedKronosTransactionThenFail(5)
        }

        assertRows(accountCount = 0, auditCount = 0)
    }

    @Test
    fun `Kronos transaction commits without an outer Spring transaction`() {
        service.createInKronosTransaction(6)

        assertRows(accountCount = 1, auditCount = 1)
    }

    @Test
    fun `Kronos transaction rolls back without an outer Spring transaction`() {
        assertThrows(IllegalStateException::class.java) {
            service.createInKronosTransactionThenFail(7)
        }

        assertRows(accountCount = 0, auditCount = 0)
    }

    private fun assertRows(accountCount: Int, auditCount: Int) {
        assertEquals(accountCount, TransactionAccount().select().toList().size)
        assertEquals(auditCount, TransactionAudit().select().toList().size)
    }
}
