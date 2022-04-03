package com.fabric.ledgerco;

import java.util.Objects;

public class Balance {
    private final String bankName;
    private final String borrower;
    private final int amountPaid;
    private final int emisLeft;

    public Balance(String bankName, String borrower, int amountPaid, int emisLeft) {
        this.bankName = bankName;
        this.borrower = borrower;
        this.amountPaid = amountPaid;
        this.emisLeft = emisLeft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Balance balance = (Balance) o;
        return amountPaid == balance.amountPaid && emisLeft == balance.emisLeft && bankName.equals(balance.bankName) && borrower.equals(balance.borrower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName, borrower, amountPaid, emisLeft);
    }
}
