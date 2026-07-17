package com.kotlinorm.common

import com.kotlinorm.beans.task.KronosAtomicBatchTask
import com.kotlinorm.beans.task.TransactionScope
import com.kotlinorm.enums.TransactionIsolation
import com.kotlinorm.interfaces.KAtomicActionTask
import com.kotlinorm.interfaces.KAtomicQueryTask
import com.kotlinorm.interfaces.KronosDataSourceWrapper
import com.kotlinorm.wrappers.KronosJdbcWrapper
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.DataSourceUtils
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import org.springframework.transaction.TransactionDefinition
import org.springframework.transaction.support.DefaultTransactionDefinition
import org.springframework.transaction.support.TransactionTemplate
import javax.sql.DataSource

/**
 * Keeps JDBC execution in Kronos while making Spring the transaction owner.
 */
class SpringKronosDataSourceWrapper(
    private val dataSource: DataSource,
    private val transactionManager: DataSourceTransactionManager
) : KronosDataSourceWrapper {

    private val delegate = KronosJdbcWrapper(TransactionAwareDataSourceProxy(dataSource))

    init {
        require(transactionManager.dataSource === dataSource) {
            "Kronos and the Spring transaction manager must use the same DataSource"
        }
    }

    override val url = delegate.url
    override val userName = delegate.userName
    override val dbType = delegate.dbType

    override fun toList(task: KAtomicQueryTask): List<Any?> = delegate.toList(task)

    override fun first(task: KAtomicQueryTask): Any? = delegate.first(task)

    override fun update(task: KAtomicActionTask): Int = delegate.update(task)

    override fun batchUpdate(task: KronosAtomicBatchTask): IntArray = delegate.batchUpdate(task)

    override fun transact(
        isolation: TransactionIsolation?,
        timeout: Int?,
        block: TransactionScope.() -> Any?
    ): Any? {
        val definition = DefaultTransactionDefinition().apply {
            propagationBehavior = TransactionDefinition.PROPAGATION_REQUIRED
            isolation?.let { isolationLevel = it.level }
            timeout?.let { this.timeout = it }
        }

        return TransactionTemplate(transactionManager, definition).execute {
            val connection = DataSourceUtils.getConnection(dataSource)
            try {
                TransactionScope(connection).block()
            } finally {
                DataSourceUtils.releaseConnection(connection, dataSource)
            }
        }
    }
}
