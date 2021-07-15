package com.daofab.transactiondata.provider;

import com.daofab.transactiondata.contants.TestConstant;
import com.daofab.transactiondata.model.ChildTransaction;
import com.daofab.transactiondata.model.ChildTransactionData;
import com.daofab.transactiondata.model.ParentTransaction;
import com.daofab.transactiondata.model.ParentTransactionData;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TransactionDataProviderImplTest {

    TransactionDataProviderImpl transactionDataProvider;

    @BeforeAll
    public void init(){
        transactionDataProvider = new TransactionDataProviderImpl();
    }

    @Test
    public void getChildTransactionDetailsTest(){
        List<ChildTransaction> childTransactionDetails = transactionDataProvider.getChildTransactionDetails();
        assertTrue(childTransactionDetails.size() > 0);
        assertFalse(childTransactionDetails.isEmpty());
        assertNotNull(childTransactionDetails);
    }

    @Test
    public void getParentTransactionDetailsTest(){
        List<ParentTransaction> parentTransactionDetails = transactionDataProvider.getParentTransactionDetails();
        assertTrue(parentTransactionDetails.size() > 0);
        assertFalse(parentTransactionDetails.isEmpty());
        assertNotNull(parentTransactionDetails);
    }

    @Test
    public void parentTransactionJsonReaderTest() throws IOException {
        ParentTransactionData parentTransactionData = transactionDataProvider.parentTransactionJsonReader(TestConstant.PARENT_TRANSACTION_RECORD_PATH);
        assertNotNull(parentTransactionData);
        assertTrue(parentTransactionData.getData().size() > 0);
        assertFalse(parentTransactionData.getData().isEmpty());
        assertNotNull(parentTransactionData.getData());
    }

    @Test
    public void childTransactionJsonReaderTest() throws IOException {
        ChildTransactionData childTransactionData = transactionDataProvider.childTransactionJsonReader(TestConstant.CHILD_TRANSACTION_RECORD_PATH);
        assertNotNull(childTransactionData);
        assertTrue(childTransactionData.getData().size() > 0);
        assertFalse(childTransactionData.getData().isEmpty());
        assertNotNull(childTransactionData.getData());
    }

}