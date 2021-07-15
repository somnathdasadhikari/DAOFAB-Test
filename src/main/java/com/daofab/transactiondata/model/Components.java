package com.daofab.transactiondata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Components {
    private DiskSpace diskSpace;
    private Ping ping;
}
