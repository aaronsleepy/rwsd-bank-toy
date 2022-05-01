package com.socurites.bank.processor;

import com.socurites.bank.domain.BankTransaction;

import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

public class BankStatementProcessor {
    public double calculateTotalAmount(List<BankTransaction> bankTransactions) {
        return bankTransactions.stream()
                .mapToDouble(BankTransaction::getAmount)
                .sum();
    }

    public double selectInMonth(List<BankTransaction> bankTransactions, Month month) {
        return calculateTotalAmount(
                bankTransactions.stream()
                        .filter(t -> month == t.getDate().getMonth())
                        .collect(Collectors.toList())
        );
    }

    public double calculateTotalForCategory(List<BankTransaction> bankTransactions, String category) {
        return calculateTotalAmount(
            bankTransactions.stream()
                    .filter(t -> t.getDescription().equals(category))
                    .collect(Collectors.toList())
        );
    }
}
