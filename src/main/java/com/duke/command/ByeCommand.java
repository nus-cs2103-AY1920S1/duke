package com.duke.command;

/**
 * Encapsulates a bye command for exiting the program.
 */
public class ByeCommand extends Command {
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    @Override
    public String execute() {
        System.exit(0);
        return BYE_MESSAGE;
    }

    @Override
    public boolean isByeCommand() {
        return true;
    }
}
