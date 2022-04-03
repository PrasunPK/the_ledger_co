package com.fabric.ledgerco;

import com.fabric.ledgerco.exception.InvalidPropertyException;

public class Loan {
    private final String bankName;
    private final String borrowerName;
    private final int principal;
    private final int noOfYears;
    private final int rateOfInterest;

    private final static int TWELVE_MONTHS = 12;
    private final static int HUNDRED = 100;

    private Loan(String bankName, String borrowerName, int principal, int noOfYears, int rateOfInterest) {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
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
        int interest = (principal * noOfYears * rateOfInterest) / HUNDRED;

        int totalAmountPayable = principal + interest;

        return totalAmountPayable / (noOfYears * TWELVE_MONTHS);
    }

    public Balance getRemainingAmount(int monthsPaid) {
        int amountPaid = getMonthlyEmiAmount() * monthsPaid;

        int remainingNoOfMonths = (noOfYears * TWELVE_MONTHS) - monthsPaid;

        return new Balance(this.bankName, this.borrowerName, amountPaid, remainingNoOfMonths);
    }

    public boolean isSame(String bankName, String borrower) {
        return this.borrowerName.equals(borrower) && this.bankName.equals(bankName);
    }
}
