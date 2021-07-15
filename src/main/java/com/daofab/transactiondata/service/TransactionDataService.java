package com.daofab.transactiondata.service;

import com.daofab.transactiondata.model.ChildTransactions;
import com.daofab.transactiondata.model.HealthInfo;
import com.daofab.transactiondata.model.ParentTransactions;

import java.util.List;

public interface TransactionDataService {
    public HealthInfo getApplicationHealthInformation();
    public List<ParentTransactions> getParentTransactionInfo(int page) throws Exception;
    public List<ChildTransactions> getChildTransactionInfo();
}
