package com.socurites.bank.analyzer;

import com.socurites.bank.domain.BankTransaction;
import com.socurites.bank.domain.SummaryStatistics;
import com.socurites.bank.parser.BankStatementCSVParser;
import com.socurites.bank.parser.BankStatementParser;
import com.socurites.bank.processor.BankStatementProcessor;
import com.socurites.bank.reporter.Exporter;
import com.socurites.bank.reporter.HtmlExporter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {
    public static final String RESOURCES = "src/main/resources/";

    public static void main(String[] args) throws IOException {
        analyze(args[0], new BankStatementCSVParser(), new HtmlExporter());
    }

    private static void analyze(String fileName, BankStatementParser bankStatementParser, Exporter exporter) throws IOException {

        final Path path = Paths.get(RESOURCES + fileName);
        List<String> lines = Files.readAllLines(path);

        final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);

        BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
        SummaryStatistics summaryStatistics = bankStatementProcessor.summarizeTransactions();

        collectSummary(exporter, summaryStatistics);
    }

    private static void collectSummary(final Exporter exporter, final SummaryStatistics summaryStatistics) {
        System.out.println(exporter.export(summaryStatistics));
    }
}
