package duke.component;

import duke.command.Command;
import duke.command.ListCommand;
import duke.command.DoneCommand;
import duke.command.TodoCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.exception.DukeException;

/**
 * Parser class will parse the input from user and
 * check for the type of task enter.
 *
 * @author TeoShyanJie
 *
 */
public class Parser {
    /**
     * Parse method to parse command enter by user.
     * @param line The input enter by user.
     * @return The command type of user.
     * @throws DukeException If command enter is not valid.
     */
    public static Command parse(String line) throws DukeException {
        assert(!line.isEmpty()): "Input are not allow to be empty";
        String command = "";
        String rest = "";

        if (!line.contains(" ")) {
            command = line;
        } else {
            String[] data = line.split(" ", 2);
            command = data[0];
            rest = data[1];
        }

        switch (command) {
        case "list":
            return new ListCommand("", "list");

        case "done":
            return new DoneCommand(rest, "done");

        case "todo":
            return new TodoCommand(rest, "todo");

        case "deadline":
            return new DeadlineCommand(rest, "deadline");

        case "event":
            return new EventCommand(rest, "event");

        case "bye":
            return new ExitCommand("", "bye");

        case "delete":
            return new DeleteCommand(rest, "delete");

        case "find":
            return new FindCommand(rest, "find");

        default:
            throw new DukeException("OOPS !!! "
                    + "I'm sorry, but I don't know what that means :-(");
        }
    }
}