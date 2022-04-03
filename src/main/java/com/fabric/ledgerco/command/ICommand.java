package com.fabric.ledgerco.command;

import com.fabric.ledgerco.exception.InvalidPropertyException;
import com.fabric.ledgerco.exception.LoanNotFoundException;

public interface ICommand {
    ICommandResult execute() throws InvalidPropertyException, LoanNotFoundException;
}
