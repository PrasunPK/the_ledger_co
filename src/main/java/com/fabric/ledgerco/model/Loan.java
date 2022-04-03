package com.fabric.ledgerco.model;

import com.fabric.ledgerco.client.ICommandResult;
import com.fabric.ledgerco.exception.InvalidPropertyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Loan implements ICommandResult {
    private final String bank;
    private final String borrower;
    private final int principal;
    private final int noOfYears;
    private final int rateOfInterest;
    private final List<Payment> transactions = new ArrayList<>();

    private final static int TWELVE_MONTHS = 12;
    private final static int HUNDRED = 100;

    private Loan(String bankName, String borrowerName, int principal, int noOfYears, int rateOfInterest) {
        this.bank = bankName;
        this.borrower = borrowerName;
        this.principal = principal;
        this.noOfYears = noOfYears;
        this.rateOfInterest = rateOfInterest;
    }

    public static Loan createLoan(String bankName, String borrowerName, int principal, int noOfYears, int rateOfInterest) throws InvalidPropertyException {
        if (principal <= 0) {
            throw new InvalidPropertyException("Principal cannot be zero");
        } else if (noOfYears <= 0) {
            throw new InvalidPropertyException("NoOfYears cannot be zero");
        } else if (rateOfInterest <= 0) {
            throw new InvalidPropertyException("RateOfInterest cannot be zero");
        } else {
            return new Loan(bankName, borrowerName, principal, noOfYears, rateOfInterest);
        }
    }

    public int getMonthlyEmiAmount() {
        double interest = (double) (principal * noOfYears * rateOfInterest) / HUNDRED;

        double totalAmountPayable = principal + interest;

        return (int) Math.ceil(totalAmountPayable / (noOfYears * TWELVE_MONTHS));
    }

    public Balance getRemainingAmount(int monthsPaid) {
        int totalLumpSumPaidAmount = calculateTotalLumpSumPayment(monthsPaid);
        int monthlyEmiAmount = getMonthlyEmiAmount();
        int amountPaid = (monthlyEmiAmount * monthsPaid) + totalLumpSumPaidAmount;
        int remainingNoOfMonths = (noOfYears * TWELVE_MONTHS) - (amountPaid / monthlyEmiAmount);

        return new Balance(this.bank, this.borrower, amountPaid, remainingNoOfMonths);
    }

    private int calculateTotalLumpSumPayment(int monthsPaid) {
        List<Payment> filteredTransactions = transactions
                .stream()
                .filter(payment -> payment.isSame(this.bank, this.borrower) && payment.isBelow(monthsPaid))
                .collect(Collectors.toList());
        int totalPaidAmount = 0;
        for (Payment payment : filteredTransactions) {
            totalPaidAmount += payment.getAmount();
        }

        return totalPaidAmount;
    }

    public boolean isSame(String bankName, String borrower) {
        return this.borrower.equals(borrower) && this.bank.equals(bankName);
    }

    public void transact(Payment payment) {
        transactions.add(payment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return principal == loan.principal && noOfYears == loan.noOfYears && rateOfInterest == loan.rateOfInterest && bank.equals(loan.bank) && borrower.equals(loan.borrower) && Objects.equals(transactions, loan.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bank, borrower, principal, noOfYears, rateOfInterest, transactions);
    }
}
