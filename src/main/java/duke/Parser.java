package duke;

import duke.commands.*;

public class Parser {
    private static final String EMPTY_TODO_STRING = "The description of a todo cannot be empty.";
    private static final String EMPTY_EVENT_STRING = "The description of a event cannot be empty.";
    private static final String EMPTY_DEADLINE_STRING = "The description of a deadline cannot be empty.";
    private static final String UNKNOWN_COMMAND_STRING = "I'm sorry, but I don't know what that means :-(";

    public static Command parse(String input) throws DukeException {
        String[] args = input.split(" ");
        return create(input, args);
    }

    private static Command create(String input, String[] args) throws DukeException {
        int numArgs = args.length;
        switch(args[0]) {
            case "bye":
                return ByeCommand.create(input, args);
            case "list":
                return ListCommand.create(input, args);
            case "done":
                return DoneCommand.create(input, args);
            case "delete":
                return DeleteCommand.create(input, args);
            case "todo":
                if (numArgs == 1) {
                    throw new DukeException(EMPTY_TODO_STRING);
                }
                return new AddCommand(input);
            case "event":
                if (numArgs == 1) {
                    throw new DukeException(EMPTY_EVENT_STRING);
                }
                return new AddCommand(input);
            case "deadline":
                if (numArgs == 1) {
                    throw new DukeException(EMPTY_DEADLINE_STRING);
                }
                return new AddCommand(input);
            default:
                throw new DukeException(UNKNOWN_COMMAND_STRING);
        }
    }
}
