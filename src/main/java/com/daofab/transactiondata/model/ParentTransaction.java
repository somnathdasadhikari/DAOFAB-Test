package com.daofab.transactiondata.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentTransaction {
    private int id;
    private String sender;
    private String receiver;
    private int totalAmount;
}
