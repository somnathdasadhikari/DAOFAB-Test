package com.daofab.transactiondata.provider;

import com.daofab.transactiondata.model.ChildTransaction;
import com.daofab.transactiondata.model.ParentTransaction;

import java.util.List;

public interface TransactionDataProvider {
    public List<ChildTransaction> getChildTransactionDetails();
    public List<ParentTransaction> getParentTransactionDetails();
}
