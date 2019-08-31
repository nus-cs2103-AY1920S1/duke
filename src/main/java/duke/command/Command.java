package duke.command;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Abstract class representing individual commands.
 */
public abstract class Command {
    boolean exit = false;

    /**
     * Determines whether this command is exiting, i.e. whether the program should exit
     * after executing this command.
     *
     * @return Whether this command is exiting.
     */
    public boolean isExit() {
        return exit;
    }
    
    /**
     * Executes this command on the given task list and user interface.
     * Returns the response to this input, which is seen by the user.
     *
     * @param tl The task list.
     * @param storage The place where tasks will be stored.
     * @return The response to the user's command.
     */
    public abstract String execute(TaskList tl, Storage storage);

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
