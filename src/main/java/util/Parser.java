package util;

import command.*;

public class Parser {

    public static Command parse(String fullCommand) {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.contains("done")) {
            return new DoneCommand();
        } else if (fullCommand.contains("delete")) {
            return new DeleteCommand();
        } else if (fullCommand.contains("todo") || fullCommand.contains("deadline") || fullCommand.contains("event")) {
            return new AddCommand();
        }
        return new InvalidCommand();
    }

}
