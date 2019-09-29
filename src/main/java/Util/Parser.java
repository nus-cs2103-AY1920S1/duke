package util;

import command.DetectCommand;
import command.Command;
import command.FindCommand;
import command.ExitCommand;
import command.AddCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.ListCommand;
import command.UnknownCommand;

public class Parser {

    /**
     * Parse the inputCommand according to user input.
     *
     * @param command user input
     * @return parse to corresponding inputCommand
     */
    public static Command parse(String command) {
        assert (!command.isEmpty()) : "Cannot parse an empty string";
        String[] op = command.split(" ");
        switch (op[0]) {
            case "bye":
                return new ExitCommand(command);
            case "todo":
            case "event":
            case "deadline":
                return new AddCommand(command);
            case "delete":
                return new DeleteCommand(command);
            case "list":
                return new ListCommand(command);
            case "done":
                return new DoneCommand(command);
            case "find":
                return new FindCommand(command);
            case "check":
                return new DetectCommand(command);
            default:
                return new UnknownCommand(command);
        }

    }
}

