package com.daofab.transactiondata.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthInfo {
    @Schema(
            description = "status",
            example = "\"status\": \"UP\""
    )
    private String status;
    @Schema(
            description = "Components details",
            example = "\"components\": {\n" +
                    "        \"diskSpace\": {\n" +
                    "            \"status\": \"UP\",\n" +
                    "            \"details\": {\n" +
                    "                \"total\": 254112686080,\n" +
                    "                \"free\": 38831935488,\n" +
                    "                \"threshold\": 10485760,\n" +
                    "                \"exists\": true\n" +
                    "            }\n" +
                    "        },\n" +
                    "        \"ping\": {\n" +
                    "            \"status\": \"UP\"\n" +
                    "        }\n" +
                    "    }"
    )
    private Components components;
}
