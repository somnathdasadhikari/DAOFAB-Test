package com.daofab.transactiondata.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParentTransactions implements Comparable<ParentTransactions>{
    @Schema(description = "Transaction id", example = "1")
    private int id;
    @Schema(description = "Sender name", example = "ABC")
    private String sender;
    @Schema(description = "Receiver name", example = "XYZ")
    private String receiver;
    @Schema(description = "Total amount", example = "200")
    private int totalAmount;
    @Schema(description = "Total paid amount", example = "100")
    private int totalPaidAmount;

    @Override
    public int compareTo(ParentTransactions parentTransactions) {
        if(this.getId() == parentTransactions.getId())
            return 0;
        else if (this.getId() > parentTransactions.getId())
            return 1;
        else
            return -1;
    }
}
