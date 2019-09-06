package puke.command;

import puke.storage.Storage;
import puke.task.Deadline;
import puke.task.Event;
import puke.task.TaskList;
import puke.task.Todo;

/**
 * Abstract class representing individual commands.
 */
public abstract class Command {
    /**
     * Executes this command on the given parameters.
     * rStrings is modified to contain this program's response to the command.
     *
     * @param tl The task list.
     * @param respStrings The ResponseStrings object operated on.
     * @param storage The place where tasks will be stored.
     */
    public abstract void execute(TaskList tl, ResponseStrings respStrings, Storage storage);

    /**
     * Parses a command, returning a corresponding Command object.
     *
     * @param cmd The command input from the GUI's entry field.
     * @return The corresponding Command object.
     */
    public static Command parse(String cmd) {
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
        assert parts.length == 2;
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
