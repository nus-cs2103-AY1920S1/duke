package execution;

import exception.DukeException;
import execution.commands.*;

public class Parser {

    public static Command parse(String line) throws DukeException {
        String[] split = line.split(" ");
        String restOfInput = getRestOfInput(split);
        return parseForCommand(split[0], restOfInput);

    }

    private static Command parseForCommand(String action, String rest) throws DukeException {
        switch (action) {
            case "bye":
                return new ByeCommand(rest);

            case "list":
                return new ListCommand(rest);

            case "done":
                return new DoneCommand(rest);

            case "todo":
                return new TodoCommand(rest);

            case "deadline":
                return new DeadlineCommand(rest);

            case "event":
                return new EventCommand(rest);

            case "delete":
                return new DeleteCommand(rest);

            case "find":
                return new FindCommand(rest);

            default:
                System.out.println("ERROR: " + rest);
                throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static String getRestOfInput(String[] split) {
        String rest = "";
        for (int i = 1; i < split.length; i++) {
            rest += split[i];
            rest += " ";
        }
        return rest;
    }
}
