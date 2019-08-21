package duke.commands;

import duke.Duke;
import duke.DukeException;

public abstract class Command {
    private static final String EMPTY_TODO_STRING = "☹ OOPS!!! The description of a todo cannot be empty.";
    private static final String EMPTY_EVENT_STRING = "☹ OOPS!!! The description of a event cannot be empty.";
    private static final String EMPTY_DEADLINE_STRING = "☹ OOPS!!! The description of a deadline cannot be empty.";
    private static final String UNKNOWN_COMMAND_STRING = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    protected Duke duke;
    protected String input;

    public Command(Duke duke, String input) {
        this.duke = duke;
        this.input = input;
    }

    public abstract void execute();

    public static Command create(Duke duke, String input) throws DukeException {
        String[] args = input.split(" ");
        int numArgs = args.length;
        switch(args[0]) {
            case "bye":
                return new ByeCommand(duke, input);
            case "list":
                return new ListCommand(duke, input);
            case "done":
                return new DoneCommand(duke, input);
            case "todo":
                if (numArgs == 1) {
                    throw new DukeException(EMPTY_TODO_STRING);
                }
                return new AddCommand(duke, input);
            case "event":
                if (numArgs == 1) {
                    throw new DukeException(EMPTY_EVENT_STRING);
                }
                return new AddCommand(duke, input);
            case "deadline":
                if (numArgs == 1) {
                    throw new DukeException(EMPTY_DEADLINE_STRING);
                }
                return new AddCommand(duke, input);
            default:
                throw new DukeException(UNKNOWN_COMMAND_STRING);
        }
    }
}
