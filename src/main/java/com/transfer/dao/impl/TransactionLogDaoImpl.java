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
        return new TransactionLog(transactionLogRecord.getFromAccountNumber(),
                                    transactionLogRecord.getToAccountNumber(),
                                    transactionLogRecord.getAmount(),
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
    public TransactionLog saveTransactionLog(Configuration outer, TransactionLog transactionLog) {
        return DSL.using(outer)
                .transactionResult(nested -> {
                    DSLContext ctx = DSL.using(nested);
                    TransactionLogRecord transactionLogRecord = ctx.newRecord(TRANSACTION_LOG);
                    transactionLogRecord.setAmount(transactionLog.getAmount());
                    transactionLogRecord.setTimeStamp(transactionLog.getTimeStamp());
                    transactionLogRecord.setFromAccountNumber(transactionLog.getFromAccountNumber());
                    //transactionLogRecord.setToAccountNumber(transactionLog.getToAccountNumber());
                    transactionLogRecord.store();

                    return transactionLogRecord.map(transactionLogMapper);
                });
    }
}
