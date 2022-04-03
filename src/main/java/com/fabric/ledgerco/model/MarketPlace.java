package com.fabric.ledgerco.model;

import com.fabric.ledgerco.exception.InvalidPropertyException;
import com.fabric.ledgerco.exception.LoanNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MarketPlace {

    private final List<Loan> loans = new ArrayList<>();

    public void createLoan(String bankName, String borrowerName, int principal, int noOfYears, int rateOfInterest)
            throws InvalidPropertyException {
        Loan loan = Loan.createLoan(bankName, borrowerName, principal, noOfYears, rateOfInterest);
        loans.add(loan);
    }

    public Balance balance(String bankName, String borrower, int emiNo) throws LoanNotFoundException {
        Loan queriedLoan = findLoan(bankName, borrower);
        return queriedLoan.getRemainingAmount(emiNo);
    }

    private Loan findLoan(String bankName, String borrower) throws LoanNotFoundException {
        Loan queriedLoan = loans
                .stream()
                .filter((loan -> loan.isSame(bankName, borrower)))
                .collect(Collectors.toList())
                .stream()
                .findFirst()
                .orElse(null);

        if (queriedLoan == null) {
            throw new LoanNotFoundException();
        }
        return queriedLoan;
    }

    public void makePayment(String bank, String borrower, int lumpSumAmount, int emiNo) throws LoanNotFoundException {
        Loan loan = findLoan(bank, borrower);
        Payment payment = new Payment(bank, borrower, lumpSumAmount, emiNo);
        loan.transact(payment);
    }
}
