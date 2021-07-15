package com.daofab.transactiondata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Details {
    private long total;
    private long free;
    private int threshold;
    private boolean exists;
}
