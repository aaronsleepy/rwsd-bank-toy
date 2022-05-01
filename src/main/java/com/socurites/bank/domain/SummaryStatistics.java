package com.socurites.bank.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SummaryStatistics {
    private final double sum;
    private final double max;
    private final double min;
    private final double average;

    public SummaryStatistics(double sum, double max, double min, double average) {
        this.sum = sum;
        this.max = max;
        this.min = min;
        this.average = average;
    }
}
