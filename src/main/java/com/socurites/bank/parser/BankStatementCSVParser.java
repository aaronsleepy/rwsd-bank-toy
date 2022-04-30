package com.socurites.bank.parser;

import com.socurites.bank.domain.BankTransaction;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BankStatementCSVParser implements BankStatementParser {
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final String DELIMETER = ",";

    @Override
    public List<BankTransaction> parseLinesFrom(final List<String> lines) {
        return lines.stream()
                .map(this::parseFrom)
                .collect(Collectors.toList());
    }

    @Override
    public BankTransaction parseFrom(final String line) {
        final String[] columns = line.split(DELIMETER);

        return BankTransaction.builder()
                .date(LocalDate.parse(columns[0], DATE_PATTERN))
                .amount(Double.parseDouble(columns[1]))
                .description(columns[2])
                .build();
    }
}
