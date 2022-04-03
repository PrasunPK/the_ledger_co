package com.fabric.ledgerco;

import com.fabric.ledgerco.exception.InvalidPropertyException;
import com.fabric.ledgerco.exception.LoanNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MarketPlace {

    private List<Loan> loans = new ArrayList<>();
    private List<Payment> transactions = new ArrayList<>();

    public void createLoan(String bankName, String borrowerName, int principal, int noOfYears, int rateOfInterest) throws InvalidPropertyException {
        Loan loan = Loan.createLoan(bankName, borrowerName, principal, noOfYears, rateOfInterest);
        loans.add(loan);
    }

    public void makePayment(Payment payment) {
        transactions.add(payment);
    }

    public Balance balance(String bankName, String borrower, int emiNo) throws LoanNotFoundException {
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

        return queriedLoan.getRemainingAmount(emiNo);
    }
}
