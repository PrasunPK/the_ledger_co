package com.fabric.ledgerco.client;

public class InputParser {

    private static final String SPACE = " ";


    public static ICommand parse(String input) {
        String[] args = input.split(SPACE);

        String command = args[0];
        String arg1 = args[1];
        String arg2 = args[2];
        String arg3 = args[3];
        String arg4 = args[4];
        String arg5 = args.length == 6 ? args[5] : null;

        return CommandFactory.getCommand(command, arg1, arg2, arg3, arg4, arg5);
    }
}
