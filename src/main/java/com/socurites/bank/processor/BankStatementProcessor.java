package com.socurites.bank.processor;

import com.socurites.bank.domain.BankTransaction;

import java.time.Month;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BankStatementProcessor {
    private List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount() {
        return bankTransactions.stream()
                .mapToDouble(BankTransaction::getAmount)
                .sum();
    }

    public double calculateTotalInMonth(Month month) {
        return this.bankTransactions.stream()
                .filter(t -> month == t.getDate().getMonth())
                .collect(Collectors.toList())
                .stream().mapToDouble(BankTransaction::getAmount)
                .sum();
    }

    public double calculateTotalForCategory(String category) {
        return this.bankTransactions.stream()
            .filter(t -> t.getDescription().equals(category))
            .collect(Collectors.toList())
            .stream().mapToDouble(BankTransaction::getAmount)
            .sum();
    }

    public List<BankTransaction> findTransactions(Predicate<BankTransaction> predicate) {
        return this.bankTransactions.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
