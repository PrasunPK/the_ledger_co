package com.fabric.ledgerco.model;

import com.fabric.ledgerco.command.ICommandResult;

import java.util.Objects;

public class Balance implements ICommandResult {
    private final String bankName;
    private final String borrower;
    private final int amountPaid;
    private final int EMIsLeft;

    public Balance(String bankName, String borrower, int amountPaid, int EMIsLeft) {
        this.bankName = bankName;
        this.borrower = borrower;
        this.amountPaid = amountPaid;
        this.EMIsLeft = EMIsLeft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance = (Balance) o;
        return amountPaid == balance.amountPaid
                && EMIsLeft == balance.EMIsLeft
                && bankName.equals(balance.bankName)
                && borrower.equals(balance.borrower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName, borrower, amountPaid, EMIsLeft);
    }

    @Override
    public String toString() {
        return bankName + " " + borrower + " " + amountPaid + " " + EMIsLeft;
    }
}
