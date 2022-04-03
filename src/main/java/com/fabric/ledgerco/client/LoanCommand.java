package com.fabric.ledgerco.client;

import com.fabric.ledgerco.exception.InvalidPropertyException;
import com.fabric.ledgerco.model.MarketPlace;

public class LoanCommand implements ICommand {
    private final MarketPlace marketPlace;
    private final String arg1;
    private final String arg2;
    private final String arg3;
    private final String arg4;
    private final String arg5;

    public LoanCommand(MarketPlace marketPlace, String arg1, String arg2, String arg3, String arg4, String arg5) {
        this.marketPlace = marketPlace;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
        this.arg4 = arg4;
        this.arg5 = arg5;
    }

    @Override
    public ICommandResult execute() throws InvalidPropertyException {
        marketPlace.createLoan(arg1, arg2, Integer.parseInt(arg3), Integer.parseInt(arg4), Integer.parseInt(arg5));
        return new ICommandResult() {
            @Override
            public String toString() {
                return "";
            }
        };
    }
}
