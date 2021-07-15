package com.daofab.transactiondata.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {
    @Schema(description = "current timestamp", example = "2021-04-15T06:20:12.727+00:00")
    private Date timestamp;
    @Schema(description = "HTTP status error code", example = "404")
    private int errorCode;
    @Schema(description = "Error description and details", example = "No Transaction records are available")
    private String details;
}
