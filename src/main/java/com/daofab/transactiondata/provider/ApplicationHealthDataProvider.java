package com.daofab.transactiondata.provider;

import com.daofab.transactiondata.model.Constants;
import com.daofab.transactiondata.model.HealthInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "Application-Health-Information", url = Constants.BASE_URI)
public interface ApplicationHealthDataProvider {
    @GetMapping(Constants.ACTUATOR_HEALTH_URI)
    public HealthInfo getApplicationHealth();
}
