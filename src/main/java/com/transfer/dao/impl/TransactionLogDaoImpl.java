package com.transfer.dao.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.transfer.dao.TransactionLogDao;
import com.transfer.entity.TransactionLog;
import com.transfer.jooq.stubs.tables.records.TransactionLogRecord;
import org.jooq.*;
import org.jooq.impl.DSL;

import static com.transfer.jooq.stubs.Tables.TRANSACTION_LOG;


@Singleton
public class TransactionLogDaoImpl implements TransactionLogDao {

    private final Configuration configuration;

    private RecordMapper<Record, TransactionLog> transactionLogMapper = record -> {
        TransactionLogRecord transactionLogRecord = (TransactionLogRecord) record;
        return new TransactionLog(transactionLogRecord.getAccountNumber(),
                                    transactionLogRecord.getAmount(),
                                    transactionLogRecord.getBalance(),
                                    transactionLogRecord.getTimeStamp());
    };

    @Inject
    public TransactionLogDaoImpl(final Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public TransactionLog saveTransactionLog(TransactionLog transactionLog) {
        return saveTransactionLog(configuration, transactionLog);
    }

    @Override
    public TransactionLog saveTransactionLog(Configuration txContext, TransactionLog transactionLog) {
        return DSL.using(txContext)
                .transactionResult(nestedTx -> {
                    DSLContext ctx = DSL.using(nestedTx);
                    TransactionLogRecord transactionLogRecord = ctx.newRecord(TRANSACTION_LOG);
                    transactionLogRecord.setAmount(transactionLog.getAmount());
                    transactionLogRecord.setBalance(transactionLog.getBalance());
                    transactionLogRecord.setTimeStamp(transactionLog.getTimeStamp());
                    transactionLogRecord.setAccountNumber(transactionLog.getAccountNumber());
                    transactionLogRecord.store();

                    return transactionLogRecord.map(transactionLogMapper);
                });
    }
}
