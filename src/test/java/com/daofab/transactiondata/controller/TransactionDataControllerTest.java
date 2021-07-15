package com.daofab.transactiondata.controller;

import com.daofab.transactiondata.contants.TestConstant;
import com.daofab.transactiondata.exception.ResourceNotFoundException;
import com.daofab.transactiondata.model.ChildTransactions;
import com.daofab.transactiondata.model.HealthInfo;
import com.daofab.transactiondata.model.ParentTransactions;
import com.daofab.transactiondata.service.TransactionDataService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TransactionDataController.class)
class TransactionDataControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    TransactionDataService transactionDataService;


    @Test
    @DisplayName("Should return valid parent transaction on first page")
    public void getParentTransactionOnFirstPage() throws Exception {
        List<ParentTransactions> parentTransactions = new ArrayList<>();
        given(transactionDataService.getParentTransactionInfo(TestConstant.FIRST_PAGE)).willReturn(parentTransactions);
        mockMvc.perform( MockMvcRequestBuilders
                .get(TestConstant.PARENT_TRANSACTION_URI_FIRST_PAGE)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    @DisplayName("Should return valid parent transaction on second page")
    public void getParentTransactionOnSecondPage() throws Exception {
        List<ParentTransactions> parentTransactions = new ArrayList<>();
        given(transactionDataService.getParentTransactionInfo(TestConstant.SECOND_PAGE)).willReturn(parentTransactions);
        mockMvc.perform( MockMvcRequestBuilders
                .get(TestConstant.PARENT_TRANSACTION_URI_SECOND_PAGE)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    @DisplayName("Should return valid parent transaction on final page with remaining record when parent transaction size is less than row size")
    public void getParentTransactionOnFinalPage() throws Exception {
        List<ParentTransactions> parentTransactions = new ArrayList<>();
        given(transactionDataService.getParentTransactionInfo(TestConstant.FINAL_PAGE)).willReturn(parentTransactions);
        mockMvc.perform( MockMvcRequestBuilders
                .get(TestConstant.PARENT_TRANSACTION_URI_FINAL_PAGE)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    @DisplayName("Should return with Exception when page is zero")
    public void getParentTransactionOnPageZero() throws Exception {
        List<ParentTransactions> parentTransactions = new ArrayList<>();
        given(transactionDataService.getParentTransactionInfo(TestConstant.PAGE_ZERO))
                .willAnswer(invocation -> {throw new Exception("Page number can not be zero !");});

        mockMvc.perform( MockMvcRequestBuilders
                .get(TestConstant.PARENT_TRANSACTION_URI_PAGE_ZERO)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("Should return with Exception when page is greater that maximum size of parent transaction")
    public void getParentTransactionOnInvalidPage() throws Exception {
        List<ParentTransactions> parentTransactions = new ArrayList<>();
        given(transactionDataService.getParentTransactionInfo(TestConstant.INVALID_MAX_PAGE))
                .willAnswer(invocation -> {throw new Exception("Page size exceeded the maximum number of transaction record");});

        mockMvc.perform( MockMvcRequestBuilders
                .get(TestConstant.PARENT_TRANSACTION_URI_INVALID_MAX_PAGE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    @DisplayName("Should return with ResourceNotFoundException when parent transaction is empty or null")
    public void getParentTransactionWithResourceNotFoundException() throws Exception {
        List<ParentTransactions> parentTransactions = new ArrayList<>();
        given(transactionDataService.getParentTransactionInfo(TestConstant.INVALID_MAX_PAGE))
                .willAnswer(invocation -> {throw new ResourceNotFoundException("No Transaction record found");});

        mockMvc.perform( MockMvcRequestBuilders
                .get(TestConstant.PARENT_TRANSACTION_URI_INVALID_MAX_PAGE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should return valid child transaction on first page")
    public void getChildTransaction() throws Exception {
        List<ChildTransactions> childTransactions = new ArrayList<>();
        given(transactionDataService.getChildTransactionInfo()).willReturn(childTransactions);
        mockMvc.perform( MockMvcRequestBuilders
                .get(TestConstant.CHILD_TRANSACTION_URI)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    @DisplayName("Should return with ResourceNotFoundException when child transaction is empty or null")
    public void getChildTransactionWithResourceNotFoundException() throws Exception {
        List<ParentTransactions> parentTransactions = new ArrayList<>();
        given(transactionDataService.getChildTransactionInfo())
                .willAnswer(invocation -> {throw new ResourceNotFoundException("No Transaction record found");});

        mockMvc.perform( MockMvcRequestBuilders
                .get(TestConstant.CHILD_TRANSACTION_URI)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should return valid health information")
    public void getApplicationApiHealthInfo() throws Exception {
        HealthInfo healthInfo = new HealthInfo();
        given(transactionDataService.getApplicationHealthInformation()).willReturn(healthInfo);
        mockMvc.perform( MockMvcRequestBuilders
                .get(TestConstant.APPLICATION_HEALTH_URI)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}