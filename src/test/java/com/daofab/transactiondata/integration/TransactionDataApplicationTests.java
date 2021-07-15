package com.daofab.transactiondata.integration;

import com.daofab.transactiondata.contants.TestConstant;
import com.daofab.transactiondata.controller.TransactionDataController;
import com.daofab.transactiondata.exception.ResourceNotFoundException;
import com.daofab.transactiondata.model.ChildTransactions;
import com.daofab.transactiondata.model.HealthInfo;
import com.daofab.transactiondata.model.ParentTransactions;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWireMock(port = 8081)
@TestPropertySource(properties = {TestConstant.BASE_URI})
class TransactionDataApplicationTests {
    @Autowired
    TransactionDataController transactionDataController;

    @Test
    @DisplayName("Should return parent transaction on first page")
    public void getParentTransactionForFirstPage() throws Exception {
        //given
        stubFor(get(urlPathEqualTo(TestConstant.PARENT_TRANSACTION_URI_FIRST_PAGE))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(TestConstant.PARENT_TRANSACTION_PAGE_1_RESPONSE)));
        //when
        ResponseEntity<List<ParentTransactions>> parentTransaction = transactionDataController.getParentTransaction(TestConstant.FIRST_PAGE);
        List<ParentTransactions> parentTransactions = parentTransaction.getBody();

        //then
        assertTrue(!parentTransactions.isEmpty());
        assertTrue(parentTransactions.size() == 2);
    }

    @Test
    @DisplayName("Should return parent transaction on second page")
    public void getParentTransactionForSecondPage() throws Exception {
        //given
        stubFor(get(urlPathEqualTo(TestConstant.PARENT_TRANSACTION_URI_SECOND_PAGE))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(TestConstant.PARENT_TRANSACTION_PAGE_2_RESPONSE)));
        //when
        ResponseEntity<List<ParentTransactions>> parentTransaction = transactionDataController.getParentTransaction(TestConstant.SECOND_PAGE);
        List<ParentTransactions> parentTransactions = parentTransaction.getBody();

        //then
        assertTrue(!parentTransactions.isEmpty());
        assertTrue(parentTransactions.size() == 2);
    }

    @Test
    @DisplayName("Should return parent transaction on final page")
    public void getParentTransactionForFinalPage() throws Exception {
        //given
        stubFor(get(urlPathEqualTo(TestConstant.PARENT_TRANSACTION_URI_FINAL_PAGE))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(TestConstant.PARENT_TRANSACTION_FINAL_PAGE_RESPONSE)));
        //when
        ResponseEntity<List<ParentTransactions>> parentTransaction = transactionDataController.getParentTransaction(TestConstant.FINAL_PAGE);
        List<ParentTransactions> parentTransactions = parentTransaction.getBody();

        //then
        assertTrue(!parentTransactions.isEmpty());
        assertTrue(parentTransactions.size() == 1);
    }

    @Test
    @DisplayName("Should return with exception for page zero")
    public void getParentTransactionForPageZero() throws Exception {
        //given
        stubFor(get(urlPathEqualTo(TestConstant.PARENT_TRANSACTION_URI_PAGE_ZERO))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(TestConstant.PARENT_TRANSACTION_PAGE_0_RESPONSE)));
        //when
       assertThrows(Exception.class, ()->transactionDataController.getParentTransaction(TestConstant.PAGE_ZERO));
    }

    @Test
    @DisplayName("Should return with exception for invalid page zero")
    public void getParentTransactionForInvalidPage() throws Exception {
        //given
        stubFor(get(urlPathEqualTo(TestConstant.PARENT_TRANSACTION_URI_INVALID_MAX_PAGE))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(TestConstant.PARENT_TRANSACTION_INVALID_PAGE_RESPONSE)));
        //when
        assertThrows(Exception.class, ()->transactionDataController.getParentTransaction(TestConstant.INVALID_MAX_PAGE));
    }

    @Test
    @DisplayName("Should return with ResourceNotFoundException for empty transaction")
    public void getParentTransactionResourceNotFoundException() throws Exception {
        //given
        stubFor(get(urlPathEqualTo(TestConstant.PARENT_TRANSACTION_URI_PAGE_RESOURCE_NOT_FOUND_EXCEPTION))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(TestConstant.PARENT_TRANSACTION_RESOURCE_NOT_FOUND_RESPONSE)));
        //when
        assertThrows(Exception.class, ()->transactionDataController.getParentTransaction(TestConstant.PAGE_RESOURCE_NOT_FOUND_EXCEPTION));
    }

    @Test
    @DisplayName("Should return child transaction")
    public void getChildTransaction() throws ResourceNotFoundException {
        //given
        stubFor(get(urlPathEqualTo(TestConstant.CHILD_TRANSACTION_URI))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(TestConstant.CHILD_TRANSACTION_RESPONSE)));
        //when
        ResponseEntity<List<ChildTransactions>> childTransaction = transactionDataController.getChildTransaction();
        List<ChildTransactions> ChildTransactions = childTransaction.getBody();

        //then
        assertTrue(!ChildTransactions.isEmpty());
    }

    @Test
    @DisplayName("Should return health information")
    public void getApplicationApiHealthInfo(){
        //given
        stubFor(get(urlPathEqualTo(TestConstant.ACTUATOR_HEALTH_URI))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile(TestConstant.APPLICATION_HEALTH_RESPONSE)));
        //when
        ResponseEntity<HealthInfo> applicationApiHealthInfo = transactionDataController.getApplicationApiHealthInfo();
        HealthInfo healthInfo = applicationApiHealthInfo.getBody();

        //then
        assertTrue(healthInfo != null);
        assertEquals("UP", healthInfo.getStatus());
        assertEquals("UP", healthInfo.getComponents().getDiskSpace().getStatus());
        assertEquals(true, healthInfo.getComponents().getDiskSpace().getDetails().isExists());
        assertEquals("UP", healthInfo.getComponents().getPing().getStatus());
    }
}
