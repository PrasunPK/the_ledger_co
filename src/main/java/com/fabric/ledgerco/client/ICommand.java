package com.fabric.ledgerco.client;

import com.fabric.ledgerco.exception.InvalidPropertyException;

public interface ICommand {
    ICommandResult execute() throws InvalidPropertyException;
}
