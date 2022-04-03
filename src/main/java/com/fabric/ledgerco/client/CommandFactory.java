package com.fabric.ledgerco.client;

public class CommandFactory {
    public static ICommand getCommand(String... args) {
        if (args[0].equals("LOAN")) {
            return new LoanCommand(args[1], args[2], args[3], args[4], args[5]);
        }
        if (args[0].equals("PAYMENT")) {
            return new PaymentCommand(args[1], args[2], args[3], args[4]);
        }
        return null;
    }
}
