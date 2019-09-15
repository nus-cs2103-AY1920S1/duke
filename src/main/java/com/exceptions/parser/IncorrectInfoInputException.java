package com.exceptions.parser;

import com.exceptions.DukeException;

/**
 * Thrown when incorrect usage of commands with subcommands
 * e.g. deadline with /by, event with /at
 * These commands require additional info of subcommands
 * These info can either be absent or more than one subcommand was entered
 */
public class IncorrectInfoInputException extends DukeException {
    public IncorrectInfoInputException(String subCommand) {
        super("Please have one \"" + subCommand + "\" provided." );
    }
}