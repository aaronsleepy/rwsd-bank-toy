package com.socurites.bank.processor;

import com.socurites.bank.domain.BankTransaction;
import com.socurites.bank.domain.SummaryStatistics;

import java.time.Month;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BankStatementProcessor {
    private List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public SummaryStatistics summarizeTransactions() {
        DoubleSummaryStatistics doubleSummaryStatistics = bankTransactions.stream()
                .mapToDouble(BankTransaction::getAmount)
                .summaryStatistics();

        return SummaryStatistics.builder()
                .sum(doubleSummaryStatistics.getSum())
                .max(doubleSummaryStatistics.getMax())
                .min(doubleSummaryStatistics.getMin())
                .average(doubleSummaryStatistics.getAverage())
                .build();
    }

    public double calculateTotalAmount() {
        return bankTransactions.stream()
                .mapToDouble(BankTransaction::getAmount)
                .sum();
    }

    public double calculateTotalInMonth(Month month) {
        return summarizeTransactions(
                this.bankTransactions.stream()
                        .filter(t -> month == t.getDate().getMonth())
                        .collect(Collectors.toList())
        );
    }

    public double calculateTotalForCategory(String category) {
        return summarizeTransactions(
                this.bankTransactions.stream()
                        .filter(t -> t.getDescription().equals(category))
                        .collect(Collectors.toList())
        );
    }

    private static double summarizeTransactions(List<BankTransaction> transactions) {
        return transactions.stream()
                .mapToDouble(BankTransaction::getAmount)
                .sum();
    }

    public List<BankTransaction> findTransactions(Predicate<BankTransaction> predicate) {
        return this.bankTransactions.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public List<BankTransaction> findTransactionsGte(final int amount) {
        return findTransactions(t -> t.getAmount() >= amount);
    }
}
