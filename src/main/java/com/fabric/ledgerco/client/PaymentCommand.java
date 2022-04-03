package com.fabric.ledgerco.client;

import com.fabric.ledgerco.Payment;
import com.fabric.ledgerco.exception.InvalidPropertyException;

public class PaymentCommand implements ICommand {
    private final String arg;
    private final String arg1;
    private final String arg2;
    private final String arg3;

    public PaymentCommand(String arg, String arg1, String arg2, String arg3) {
        this.arg = arg;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
    }

    @Override
    public ICommandResult execute() {
        return new Payment(arg, arg1, Integer.parseInt(arg2), Integer.parseInt(arg3));
    }
}
