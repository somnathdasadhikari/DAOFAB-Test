package com.daofab.transactiondata.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator, HealthContributor {
    @Override
    public Health health() {
        return Health.up().build();
    }
}
