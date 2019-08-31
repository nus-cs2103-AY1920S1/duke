package duke.textual;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Parser for commands entered by the Duke user, returning Command objects.
 */
public class Parser {
    /**
     * Parses a command, returning a corresponding Command object.
     *
     * @param cmd The command input from the GUI's entry field.
     * @return The corresponding Command object.
     */
    public Command parse(String cmd) {
        if (cmd.equals("bye")) {
            return new ExitCommand();
        }
        if (cmd.equals("list")) {
            return new ListCommand();
        }

        String[] parts = cmd.split(" ", 2);
        if (parts.length == 1) {
            throw new IllegalArgumentException("Yes, but what comes next?");
        }
        String data = parts[1];

        switch (parts[0]) {
        case "done":
            return new DoneCommand(data);
        case "delete":
            return new DeleteCommand(data);
        case "todo":
            return new AddCommand(Todo.parse(data));
        case "event":
            return new AddCommand(Event.parse(data));
        case "deadline":
            return new AddCommand(Deadline.parse(data));
        case "find":
            return new FindCommand(data);
        default:
            throw new IllegalArgumentException("I don't know what that command is.");
        }
    }
}
