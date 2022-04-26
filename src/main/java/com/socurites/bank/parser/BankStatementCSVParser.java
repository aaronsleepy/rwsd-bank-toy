package com.socurites.bank.parser;

import com.socurites.bank.domain.BankTransaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class BankStatementCSVParser {
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final String DELIMETER = ",";

    public List<BankTransaction> parseLinesFromCSV(final List<String> lines) {
        return lines.stream()
                .map(BankStatementCSVParser::parseFromCSV)
                .collect(Collectors.toList());
    }

    private static BankTransaction parseFromCSV(final String line) {
        final String[] columns = line.split(DELIMETER);

        return BankTransaction.builder()
                .date(LocalDate.parse(columns[0], DATE_PATTERN))
                .amount(Double.parseDouble(columns[1]))
                .description(columns[2])
                .build();
    }
}
