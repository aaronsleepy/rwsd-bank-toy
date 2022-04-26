package com.socurites.bank.analyzer;

import com.socurites.bank.domain.BankTransaction;
import com.socurites.bank.parser.BankStatementCSVParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

public class BankTransactionAnalyzer {
    public static final String RESOURCES = "src/main/resources/";

    public static void main(String[] args) throws IOException {
        final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();

        final String fileName = args[0];
        final Path path = Paths.get(RESOURCES + fileName);
        List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(lines);

        System.out.println("The total for all transactions is " + calculateTotalAmount(bankTransactions));
        System.out.println("The total for all transactions in January is " + selectInMonth(bankTransactions, Month.JANUARY));
    }

    private static double selectInMonth(List<BankTransaction> bankTransactions, Month month) {
        return calculateTotalAmount(
                bankTransactions.stream()
                        .filter(t -> month == t.getDate().getMonth())
                        .collect(Collectors.toList())
        );
    }

    private static double calculateTotalAmount(List<BankTransaction> bankTransactions) {
        return bankTransactions.stream()
                .mapToDouble(BankTransaction::getAmount)
                .sum();
    }
}
