package com.socurites.bank.analyzer;

import com.socurites.bank.domain.BankTransaction;
import com.socurites.bank.parser.BankStatementCSVParser;
import com.socurites.bank.parser.BankStatementParser;
import com.socurites.bank.processor.BankStatementProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {
    public static final String RESOURCES = "src/main/resources/";

    public static void main(String[] args) throws IOException {
        analyze(args[0], new BankStatementCSVParser(), new BankStatementProcessor());
    }

    private static void analyze(String fileName, BankStatementParser bankStatementParser, BankStatementProcessor bankStatementProcessor) throws IOException {

        final Path path = Paths.get(RESOURCES + fileName);
        List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);

        collectSummary(bankStatementProcessor, bankTransactions);
    }

    private static void collectSummary(final BankStatementProcessor bankStatementProcessor, final List<BankTransaction> bankTransactions) {
        System.out.println("The total for all transactions is " + bankStatementProcessor.calculateTotalAmount(bankTransactions));
        System.out.println("The total for all transactions in January is " + bankStatementProcessor.selectInMonth(bankTransactions, Month.JANUARY));
        System.out.println("The total salary received is " + bankStatementProcessor.calculateTotalForCategory(bankTransactions, "Salary"));
    }
}
