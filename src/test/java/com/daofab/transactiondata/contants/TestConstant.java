package com.daofab.transactiondata.contants;

public class TestConstant {
    public static final String ACTUATOR_HEALTH_URI = "/actuator/health";
    public static final String APPLICATION_HEALTH_URI = "/api/v1/transaction-data/health";
    public static final String BASE_URI = "http://localhost:8081";
    public static final String APPLICATION_HEALTH_RESPONSE = "application-health.json";
    public static final String CHILD_TRANSACTION_RESPONSE = "child-transaction.json";
    public static final String PARENT_TRANSACTION_PAGE_0_RESPONSE = "parent-transaction-page-0.json";
    public static final String PARENT_TRANSACTION_PAGE_1_RESPONSE = "parent-transaction-page-1.json";
    public static final String PARENT_TRANSACTION_PAGE_2_RESPONSE = "parent-transaction-page-2.json";
    public static final String PARENT_TRANSACTION_FINAL_PAGE_RESPONSE = "parent-transaction-final-page.json";
    public static final String PARENT_TRANSACTION_INVALID_PAGE_RESPONSE = "parent-transaction-invalid-page.json";
    public static final String PARENT_TRANSACTION_RESOURCE_NOT_FOUND_RESPONSE = "parent-transaction-resource-not-found.json";
    public static final String PARENT_TRANSACTION_URI = "/api/v1/transaction?page=";
    public static final String CHILD_TRANSACTION_URI = "/api/v1/transaction/child";
    public static final int ROW_SIZE = 2;
    public static final int FINAL_PAGE_ROW_SIZE = 1;
    public static final int FIRST_PAGE = 1;
    public static final int SECOND_PAGE = 2;
    public static final int FINAL_PAGE = 5;
    public static final int PAGE_RESOURCE_NOT_FOUND_EXCEPTION = 6;
    public static final int INVALID_MAX_PAGE = 8;
    public static final int PAGE_ZERO = 0;
    public static final String PARENT_TRANSACTION_URI_PAGE_ZERO = PARENT_TRANSACTION_URI + PAGE_ZERO;
    public static final String PARENT_TRANSACTION_URI_FIRST_PAGE = PARENT_TRANSACTION_URI + FIRST_PAGE;
    public static final String PARENT_TRANSACTION_URI_SECOND_PAGE = PARENT_TRANSACTION_URI + SECOND_PAGE;
    public static final String PARENT_TRANSACTION_URI_FINAL_PAGE = PARENT_TRANSACTION_URI + FINAL_PAGE;
    public static final String PARENT_TRANSACTION_URI_INVALID_MAX_PAGE = PARENT_TRANSACTION_URI + INVALID_MAX_PAGE;
    public static final String PARENT_TRANSACTION_URI_PAGE_RESOURCE_NOT_FOUND_EXCEPTION = PARENT_TRANSACTION_URI + PAGE_RESOURCE_NOT_FOUND_EXCEPTION;
    public static final String PARENT_TRANSACTION_RECORD_PATH = "/jsondata/Parent.json";
    public static final String CHILD_TRANSACTION_RECORD_PATH = "/jsondata/Child.json";
}
