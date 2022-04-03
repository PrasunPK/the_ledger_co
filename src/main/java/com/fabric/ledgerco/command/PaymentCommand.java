package com.fabric.ledgerco.command;

import com.fabric.ledgerco.exception.LoanNotFoundException;
import com.fabric.ledgerco.model.MarketPlace;

public class PaymentCommand implements ICommand {
    private final MarketPlace marketPlace;
    private final String arg;
    private final String arg1;
    private final String arg2;
    private final String arg3;

    public PaymentCommand(MarketPlace marketPlace, String arg, String arg1, String arg2, String arg3) {
        this.marketPlace = marketPlace;
        this.arg = arg;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
    }

    @Override
    public ICommandResult execute() throws LoanNotFoundException {
        marketPlace.makePayment(arg, arg1, Integer.parseInt(arg2), Integer.parseInt(arg3));
        return new ICommandResult() {
            @Override
            public String toString() {
                return "";
            }
        };
    }
}
