package com.transfer.dao;

import com.transfer.entity.TransactionLog;
import org.jooq.Configuration;

public interface TransactionLogDao extends Dao {

    TransactionLog saveTransactionLog(TransactionLog transactionLog);

    TransactionLog saveTransactionLog(Configuration outer, TransactionLog transactionLog);
}
