package duke.core;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.commands.DoneCommand;
import duke.commands.DeleteCommand;

import duke.errors.DukeException;

import java.util.List;

public class Parser {
    public static Command parse(String input) throws DukeException, IllegalArgumentException {
        String[] tokens = input.split(" ");
        if (tokens[0].equals("bye")) {
            return new ExitCommand();
        } else if (tokens[0].equals("list")) {
            return new ListCommand();
        } else if (tokens[0].equals("done")) {
            return DoneCommand.createDoneIfValid(tokens);
        } else if (tokens[0].equals("delete")) {
            return DeleteCommand.createDeleteIfValid(tokens);
        } else {
            return Command.createAddCommandIfValid(tokens);
        }
    }

    public static void checkValidLength(String[] tokens) throws IllegalArgumentException {
        List<String> group1 = List.of("todo", "deadline", "event");
        List<String> group2 = List.of("done", "delete");
        if (tokens.length == 1 && group1.contains(tokens[0])) {
            throw new IllegalArgumentException(String.format("☹ OOPS!!! The description of a %s cannot be empty.",tokens[0]));
        } else if (tokens.length == 1 && group2.contains(tokens[0])) {
            throw new IllegalArgumentException(String.format("☹ OOPS!!! %s command requires integer.",tokens[0]));
        }
    }
}






