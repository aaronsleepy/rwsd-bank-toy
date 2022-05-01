package com.socurites.bank.reporter;

import com.socurites.bank.domain.SummaryStatistics;

public interface Exporter {
    String export(SummaryStatistics summaryStatistics);
}
