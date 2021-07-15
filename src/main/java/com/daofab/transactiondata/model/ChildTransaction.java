package com.daofab.transactiondata.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChildTransaction {
    private int id;
    private int parentId;
    private int paidAmount;
}
