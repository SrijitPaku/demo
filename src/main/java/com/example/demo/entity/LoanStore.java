package com.example.demo.entity;

import java.util.Date;

public class LoanStore {
    private String loanId;
    private String customerId;
    private String lenderId;
    private double amount;
    private double remainingAmount;
    private Date paymentDate;
    private double interestPerDay;
    private Date dueDate;
    private double penaltyPerDay;
    private boolean canceled;

    private LoanStore(LoanStoreBuilder builder) {
        this.loanId = builder.loanId;
        this.customerId = builder.customerId;
        this.lenderId = builder.lenderId;
        this.amount = builder.amount;
        this.remainingAmount = builder.remainingAmount;
        this.paymentDate = builder.paymentDate;
        this.interestPerDay = builder.interestPerDay;
        this.dueDate = builder.dueDate;
        this.penaltyPerDay = builder.penaltyPerDay;
        this.canceled = builder.canceled;
    }

    public static class LoanStoreBuilder {
        private String loanId;
        private String customerId;
        private String lenderId;
        private double amount;
        private double remainingAmount;
        private Date paymentDate;
        private double interestPerDay;
        private Date dueDate;
        private double penaltyPerDay;
        private boolean canceled;

        public LoanStoreBuilder(String loanId, String customerId, String lenderId) {
            this.loanId = loanId;
            this.customerId = customerId;
            this.lenderId = lenderId;
        }

        public LoanStoreBuilder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public LoanStoreBuilder remainingAmount(double remainingAmount) {
            this.remainingAmount = remainingAmount;
            return this;
        }

        public LoanStoreBuilder paymentDate(Date paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public LoanStoreBuilder interestPerDay(double interestPerDay) {
            this.interestPerDay = interestPerDay;
            return this;
        }

        public LoanStoreBuilder dueDate(Date dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public LoanStoreBuilder penaltyPerDay(double penaltyPerDay) {
            this.penaltyPerDay = penaltyPerDay;
            return this;
        }

        public LoanStoreBuilder canceled(boolean canceled) {
            this.canceled = canceled;
            return this;
        }

        public LoanStore build() {
            return new LoanStore(this);
        }
    }

    public String getLoanId() {
        return loanId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getLenderId() {
        return lenderId;
    }

    public double getAmount() {
        return amount;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public double getInterestPerDay() {
        return interestPerDay;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public double getPenaltyPerDay() {
        return penaltyPerDay;
    }

    public boolean isCanceled() {
        return canceled;
    }

    @Override
    public String toString() {
        return "LoanStore{" +
                "loanId='" + loanId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", lenderId='" + lenderId + '\'' +
                ", amount=" + amount +
                ", remainingAmount=" + remainingAmount +
                ", paymentDate=" + paymentDate +
                ", interestPerDay=" + interestPerDay +
                ", dueDate=" + dueDate +
                ", penaltyPerDay=" + penaltyPerDay +
                ", canceled=" + canceled +
                '}';
    }
}
