package com.example.demo.service;

import com.example.demo.entity.LoanStore;

import java.util.*;
import java.util.stream.Collectors;
import java.util.logging.Logger;

public class LoanService {
    private Logger logger;

    public LoanService(Logger logger) {
        this.logger = logger;
    }

    public LoanStore createLoan(String loanId, String customerId, String lenderId, double amount,

                                double remainingAmount, Date paymentDate, double interestPerDay,
                                Date dueDate, double penaltyPerDay) {
        if (paymentDate.after(dueDate)) {
            throw new IllegalArgumentException("Payment date cannot be greater than due date");
        }

        return new LoanStore.LoanStoreBuilder(loanId, customerId, lenderId)
                .amount(amount)
                .remainingAmount(remainingAmount)
                .paymentDate(paymentDate)
                .interestPerDay(interestPerDay)
                .dueDate(dueDate)
                .penaltyPerDay(penaltyPerDay)
                .canceled(false)
                .build();
    }

    public Map<String, Double> aggregateByLender(List<LoanStore> loans) {
        return loans.stream()
                .collect(Collectors.groupingBy(LoanStore::getLenderId,
                        Collectors.summingDouble(LoanStore::getRemainingAmount)));
    }

    public Map<String, Double> aggregateByInterest(List<LoanStore> loans) {
        return loans.stream()
                .collect(Collectors.groupingBy(loan -> String.valueOf(loan.getInterestPerDay()),
                        Collectors.summingDouble(loan -> loan.getRemainingAmount() * loan.getInterestPerDay())));
    }

    public Map<String, Double> aggregateByCustomerId(List<LoanStore> loans) {
        return loans.stream()
                .collect(Collectors.groupingBy(LoanStore::getCustomerId,
                        Collectors.summingDouble(LoanStore::getRemainingAmount)));
    }

    public void checkAndLogDueDateCrossing(List<LoanStore> loans) {
        loans.stream()
                .filter(loan -> loan.getDueDate().before(new Date()))
                .forEach(loan -> logger.warning("Loan " + loan.getLoanId() + " has crossed the due date."));
    }
}
