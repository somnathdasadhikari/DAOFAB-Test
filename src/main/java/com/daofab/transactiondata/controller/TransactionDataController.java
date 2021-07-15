package com.daofab.transactiondata.controller;

import com.daofab.transactiondata.exception.ErrorDetails;
import com.daofab.transactiondata.exception.ResourceNotFoundException;
import com.daofab.transactiondata.model.ChildTransactions;
import com.daofab.transactiondata.model.HealthInfo;
import com.daofab.transactiondata.model.ParentTransactions;
import com.daofab.transactiondata.service.TransactionDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Transaction-Data", description = "The Transaction-Data API")
public class TransactionDataController {
    @Autowired
    TransactionDataService transactionDataService;

    @Operation(summary = "Get parent transaction", description = "Will provide details of parent transaction", tags = "Transaction-Data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ParentTransactions.class))),
            @ApiResponse(responseCode = "500", description = "Page number can not be zero !", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "500", description = "Page size exceeded the maximum number of transaction record", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
            @ApiResponse(responseCode = "404", description = "No Transaction record found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))) })
    @GetMapping("/transaction")
    public ResponseEntity<List<ParentTransactions>> getParentTransaction(@RequestParam(value = "page", required = true) int page) throws Exception {
        List<ParentTransactions> parentTransactionInfo = transactionDataService.getParentTransactionInfo(page);
        if(parentTransactionInfo.size() == 0 || parentTransactionInfo == null)
            throw new ResourceNotFoundException("No Transaction record found");
        return ResponseEntity.ok(parentTransactionInfo);
    }

    @Operation(summary = "Get child transaction", description = "Will provide details of child transaction", tags = "Transaction-Data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = ChildTransactions.class))),
            @ApiResponse(responseCode = "404", description = "No Transaction record found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))) })
    @GetMapping("/transaction/child")
    public ResponseEntity<List<ChildTransactions>> getChildTransaction() throws ResourceNotFoundException {
        List<ChildTransactions> childTransactionInfo = transactionDataService.getChildTransactionInfo();
        if(childTransactionInfo.size() == 0 || childTransactionInfo == null)
            throw new ResourceNotFoundException("No Transaction record found");
        return ResponseEntity.ok(childTransactionInfo);
    }

    @Operation(summary = "Get health information", description = "Will provide the health information of the running application", tags = "Transaction-TransactionData-Health-Check")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = HealthInfo.class)))})
    @GetMapping("/transaction-data/health")
    public ResponseEntity<HealthInfo> getApplicationApiHealthInfo(){
        HealthInfo applicationHealthInformation = transactionDataService.getApplicationHealthInformation();
        return ResponseEntity.ok(applicationHealthInformation);
    }
}
