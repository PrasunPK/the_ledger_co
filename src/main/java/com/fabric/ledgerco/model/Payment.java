package com.fabric.ledgerco.model;

import com.fabric.ledgerco.command.ICommandResult;

import java.util.Objects;

public class Payment implements ICommandResult {
    private final String bankName;
    private final String borrower;
    private final int lumpSumAmount;
    private final int emiNo;

    public Payment(String bankName, String borrower, int lumpSumAmount, int emiNo) {
        this.bankName = bankName;
        this.borrower = borrower;
        this.lumpSumAmount = lumpSumAmount;
        this.emiNo = emiNo;
    }

    public boolean isSame(String bankName, String borrower) {
        return this.borrower.equals(borrower) && this.bankName.equals(bankName);
    }

    public int getAmount() {
        return lumpSumAmount;
    }

    public boolean isBelow(int monthsPaid) {
        return monthsPaid >= this.emiNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return lumpSumAmount == payment.lumpSumAmount
                && emiNo == payment.emiNo
                && Objects.equals(bankName, payment.bankName)
                && Objects.equals(borrower, payment.borrower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName, borrower, lumpSumAmount, emiNo);
    }
}
