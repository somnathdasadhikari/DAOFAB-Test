package com.daofab.transactiondata.service;

import com.daofab.transactiondata.model.*;
import com.daofab.transactiondata.provider.ApplicationHealthDataProvider;
import com.daofab.transactiondata.provider.TransactionDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionDataServiceImpl implements TransactionDataService{
    private static final Logger logger = LoggerFactory.getLogger(TransactionDataServiceImpl.class);
    @Autowired
    ApplicationHealthDataProvider applicationHealthDataProvider;
    @Autowired
    TransactionDataProvider transactionDataProvider;

    @Override
    public HealthInfo getApplicationHealthInformation() {
        return applicationHealthDataProvider.getApplicationHealth();
    }

    @Override
    public List<ParentTransactions> getParentTransactionInfo(int page) throws Exception {
        int totalPaidAmount = 0;
        List<ParentTransactions> sizedParentTransactions = null;
        List<ParentTransactions> parentTransactionsList = new ArrayList<>();
        List<ParentTransaction> parentTransactionDetails = transactionDataProvider.getParentTransactionDetails();
        List<ChildTransaction> childTransactionDetails = transactionDataProvider.getChildTransactionDetails();
        for(ParentTransaction parentTransaction : parentTransactionDetails){
            int totalAmount = 0;
            for(ChildTransaction childTransaction : childTransactionDetails){
                if (parentTransaction.getId() == childTransaction.getParentId()){
                        totalAmount = totalAmount + childTransaction.getPaidAmount();
                }
            }
            totalPaidAmount = totalAmount;
            ParentTransactions parentTransactions = new ParentTransactions().builder()
                                                    .id(parentTransaction.getId())
                                                    .receiver(parentTransaction.getReceiver())
                                                    .sender(parentTransaction.getSender())
                                                    .totalAmount(parentTransaction.getTotalAmount())
                                                    .totalPaidAmount(totalPaidAmount)
                                                    .build();
            parentTransactionsList.add(parentTransactions);
        }

        if(page == 0)
            throw new Exception("Page number can not be zero !");
        else if (page >= parentTransactionsList.size())
            throw new Exception("Page size is equal or greater than transaction record");
        else
            sizedParentTransactions = getListBySize(page, parentTransactionsList);

        Collections.sort(sizedParentTransactions);
        return sizedParentTransactions;
    }

    @Override
    public List<ChildTransactions> getChildTransactionInfo() {
        List<ChildTransactions> childTransactionsList = new ArrayList<ChildTransactions>();
        List<ParentTransaction> parentTransactionDetails = transactionDataProvider.getParentTransactionDetails();
        List<ChildTransaction> childTransactionDetails = transactionDataProvider.getChildTransactionDetails();
        for(ChildTransaction childTransaction : childTransactionDetails){
            int totalAmount = 0;
            String sender = null;
            String receiver = null;
            for(ParentTransaction parentTransaction : parentTransactionDetails){
                if (parentTransaction.getId() == childTransaction.getParentId()){
                    totalAmount = parentTransaction.getTotalAmount();
                    sender = parentTransaction.getSender();
                    receiver = parentTransaction.getReceiver();
                }
            }
            ChildTransactions childTransactions = new ChildTransactions().builder()
                                                    .id(childTransaction.getId())
                                                    .sender(sender)
                                                    .receiver(receiver)
                                                    .totalAmount(totalAmount)
                                                    .paidAmount(childTransaction.getPaidAmount())
                                                    .build();
            childTransactionsList.add(childTransactions);
        }
        return childTransactionsList;
    }

    private List<ParentTransactions> getListBySize(int page, List<ParentTransactions> list) {
        int startIndex = 0;
        int rowSize = 0;
        List<ParentTransactions> parentTransactionsList = new ArrayList<>();
        if(page == 1){
            startIndex = 0;
            rowSize = startIndex + Constants.ROW_SIZE;
            parentTransactionsList = list.subList(startIndex, rowSize);
        }
        else if(page == 2){
            startIndex = page;
            rowSize = startIndex + Constants.ROW_SIZE;
            parentTransactionsList = list.subList(startIndex, rowSize);
        }
        else {
            startIndex = page + 1;
            rowSize = startIndex + Constants.ROW_SIZE;
            if(rowSize > list.size()){
                parentTransactionsList = list.subList(startIndex, list.size());
            }
            else{
                parentTransactionsList = list.subList(startIndex, rowSize);
            }
        }
        return parentTransactionsList;
    }

}
