package com.daofab.transactiondata.service;

import com.daofab.transactiondata.contants.TestConstant;
import com.daofab.transactiondata.model.ChildTransaction;
import com.daofab.transactiondata.model.ChildTransactions;
import com.daofab.transactiondata.model.ParentTransaction;
import com.daofab.transactiondata.model.ParentTransactions;
import com.daofab.transactiondata.provider.TransactionDataProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TransactionDataServiceImplTest {
    @Mock
    TransactionDataProvider transactionDataProvider;
    @InjectMocks
    TransactionDataServiceImpl transactionDataService;
    @Spy
    List<ParentTransaction> parentTransactionsList = new ArrayList<>();
    @Spy
    List<ChildTransaction> childTransactions = new ArrayList<>();



    @Test
    @DisplayName("Should return valid parent transaction on first page")
    public void getParentTransactionInfoForFirstPage() throws Exception {
        ParentTransaction parentTransactions = new ParentTransaction();
        ParentTransaction parentTransactions1 = new ParentTransaction();
        parentTransactionsList.add(parentTransactions);
        parentTransactionsList.add(parentTransactions1);
        when(transactionDataProvider.getParentTransactionDetails()).thenReturn(parentTransactionsList);
        List<ParentTransactions> parentTransactionInfo = transactionDataService.getParentTransactionInfo(TestConstant.FIRST_PAGE);
        assertNotNull(parentTransactionInfo);
        assertEquals(TestConstant.ROW_SIZE, parentTransactionInfo.size());
        verify(transactionDataProvider, atLeastOnce()).getParentTransactionDetails();
    }

    @Test
    @DisplayName("Should return valid parent transaction second page")
    public void getParentTransactionInfoForSecondPage() throws Exception {
        ParentTransaction parentTransactions = new ParentTransaction();
        ParentTransaction parentTransactions1 = new ParentTransaction();
        ParentTransaction parentTransactions2 = new ParentTransaction();
        ParentTransaction parentTransactions3 = new ParentTransaction();
        parentTransactionsList.add(parentTransactions);
        parentTransactionsList.add(parentTransactions1);
        parentTransactionsList.add(parentTransactions2);
        parentTransactionsList.add(parentTransactions3);
        when(transactionDataProvider.getParentTransactionDetails()).thenReturn(parentTransactionsList);
        List<ParentTransactions> parentTransactionInfo = transactionDataService.getParentTransactionInfo(TestConstant.SECOND_PAGE);
        assertNotNull(parentTransactionInfo);
        assertEquals(TestConstant.ROW_SIZE, parentTransactionInfo.size());
        verify(transactionDataProvider, atLeastOnce()).getParentTransactionDetails();
    }

    @Test
    @DisplayName("Should return valid parent transaction final page")
    public void getParentTransactionInfoForFinalPage() throws Exception {
        ParentTransaction parentTransactions = new ParentTransaction();
        ParentTransaction parentTransactions1 = new ParentTransaction();
        ParentTransaction parentTransactions2 = new ParentTransaction();
        ParentTransaction parentTransactions3 = new ParentTransaction();
        ParentTransaction parentTransactions4 = new ParentTransaction();
        ParentTransaction parentTransactions5 = new ParentTransaction();
        ParentTransaction parentTransactions6 = new ParentTransaction();
        parentTransactionsList.add(parentTransactions);
        parentTransactionsList.add(parentTransactions1);
        parentTransactionsList.add(parentTransactions2);
        parentTransactionsList.add(parentTransactions3);
        parentTransactionsList.add(parentTransactions4);
        parentTransactionsList.add(parentTransactions5);
        parentTransactionsList.add(parentTransactions6);
        when(transactionDataProvider.getParentTransactionDetails()).thenReturn(parentTransactionsList);
        List<ParentTransactions> parentTransactionInfo = transactionDataService.getParentTransactionInfo(TestConstant.FINAL_PAGE);
        assertNotNull(parentTransactionInfo);
        assertEquals(TestConstant.FINAL_PAGE_ROW_SIZE, parentTransactionInfo.size());
        verify(transactionDataProvider, atLeastOnce()).getParentTransactionDetails();
    }

    @Test
    @DisplayName("Should return with exception for page 0")
    public void getParentTransactionInfoForPageZero() throws Exception {
        assertThrows(Exception.class, ()-> {
            transactionDataService.getParentTransactionInfo(TestConstant.PAGE_ZERO);
        });
        verify(transactionDataProvider, atLeastOnce()).getParentTransactionDetails();
    }


    @Test
    @DisplayName("Should return with exception for invalid page")
    public void getParentTransactionInfoForInvalidPage() throws Exception {
        assertThrows(Exception.class, ()-> {
            transactionDataService.getParentTransactionInfo(TestConstant.INVALID_MAX_PAGE);
        });
        verify(transactionDataProvider, atLeastOnce()).getParentTransactionDetails();
    }

    @Test
    @DisplayName("Should return valid parent transaction")
    public void getChildTransactionInfo() throws Exception {
        List<ChildTransactions> childTransactionInfo = transactionDataService.getChildTransactionInfo();
        assertNotNull(childTransactionInfo);
        when(transactionDataProvider.getChildTransactionDetails()).thenReturn(childTransactions);
        verify(transactionDataProvider, atLeastOnce()).getChildTransactionDetails();
    }
}