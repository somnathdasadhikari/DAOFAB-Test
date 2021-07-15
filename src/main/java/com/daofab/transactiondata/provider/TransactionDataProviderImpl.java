package com.daofab.transactiondata.provider;

import com.daofab.transactiondata.model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionDataProviderImpl implements TransactionDataProvider{

    private static final Logger logger = LoggerFactory.getLogger(TransactionDataProviderImpl.class);

    @Override
    public List<ChildTransaction> getChildTransactionDetails() {
        ChildTransactionData childTransactionData = null;
        List<ChildTransaction> listOfChildTransaction = new ArrayList<>();
        try {
            childTransactionData = childTransactionJsonReader(Constants.CHILD_TRANSACTION_RECORD_PATH);
        }catch (IOException e){
            logger.error("Exception Message :: "+e.getMessage());
        }
        childTransactionData.getData().stream().forEach(data ->{
            ChildTransaction childTransaction = new ChildTransaction().builder()
                    .id(data.getId())
                    .parentId(data.getParentId())
                    .paidAmount(data.getPaidAmount())
                    .build();
            listOfChildTransaction.add(childTransaction);
        });
        return listOfChildTransaction;
    }

    @Override
    public List<ParentTransaction> getParentTransactionDetails() {
        ParentTransactionData parentTransactionData = null;
        List<ParentTransaction> listOfParentTransaction = new ArrayList<>();
        try {
            parentTransactionData = parentTransactionJsonReader(Constants.PARENT_TRANSACTION_RECORD_PATH);
        } catch (IOException e) {
            logger.error("Exception Message :: "+e.getMessage());
        }
        parentTransactionData.getData().stream().forEach(data -> {
            ParentTransaction parentTransaction = new ParentTransaction().builder()
                    .id(data.getId())
                    .receiver(data.getReceiver())
                    .sender(data.getSender())
                    .totalAmount(data.getTotalAmount())
                    .build();
            listOfParentTransaction.add(parentTransaction);
        });
        return listOfParentTransaction;
    }

    public ParentTransactionData parentTransactionJsonReader(String path) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        TypeReference<ParentTransactionData> typeReference = new TypeReference<>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream(path);
        ParentTransactionData parenttransactionData = mapper.readValue(inputStream, typeReference);
        return parenttransactionData;
    }

    public ChildTransactionData childTransactionJsonReader(String path) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        TypeReference<ChildTransactionData> typeReference = new TypeReference<>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream(path);
        ChildTransactionData childtransactionData = mapper.readValue(inputStream, typeReference);
        return childtransactionData;
    }


}
