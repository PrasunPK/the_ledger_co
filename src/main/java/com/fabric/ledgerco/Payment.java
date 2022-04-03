package com.fabric.ledgerco;

public class Payment {
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
}
