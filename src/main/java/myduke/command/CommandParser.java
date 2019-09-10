package myduke.command;

import myduke.exception.DukeException;
import myduke.exception.DukeInvalidCommandException;
import myduke.type.TaskType;

/**
 * Parses a query into its respective command.
 */
public class CommandParser {

    /**
     * Creates the relevant command based on the user's query.
     *
     * @param query The query of the user.
     *
     * @return The appropriate Command.
     *
     * @throws DukeException if no commands matches the query.
     */
    public static Command create(String query) throws DukeException {

        String[] parts = query.trim().split(" ", 2);
        if (parts.length == 0 || parts[0].length() == 0) {
            throw new DukeInvalidCommandException("Query should not be empty");
        }

        String arguments = (parts.length == 2) ? parts[1] : "";

        //Try to parse User's query
        switch (parts[0]) {
        case "t":
        case "todo":
            return new AddTaskCommand(TaskType.TASK_TODO, arguments);

        case "d":
        case "due":
        case "deadline":
            return new AddTaskCommand(TaskType.TASK_DEADLINE, arguments);

        case "e":
        case "event":
            return new AddTaskCommand(TaskType.TASK_EVENT, arguments);

        case "a":
        case "after":
        case "doafter":
            return new AddTaskCommand(TaskType.TASK_DO_AFTER, arguments);

        case "m":
        case "mark":
        case "done":
            return new MarkCompletedTaskCommand(arguments);

        case "r":
        case "rm":
        case "del":
        case "delete":
            return new DeleteCommand(arguments);

        case "f":
        case "find":
            return new FilterTasksCommand(arguments);

        case "l":
        case "ls":
        case "list":
            if (parts.length != 1) {
                throw new DukeInvalidCommandException("I'm sorry, but I don't know what that means :-(");
            }
            return new ListCommand();

        case "bye":
        case "shutdown":
            if (parts.length != 1) {
                throw new DukeInvalidCommandException("I'm sorry, but I don't know what that means :-(");
            }
            return new TerminateSessionCommand();

        case "init":
        case "reload":
        case "reinitialise":
            if (parts.length != 1) {
                throw new DukeInvalidCommandException("I'm sorry, but I don't know what that means :-(");
            }
            return new InitialiseDukeCommand();

        default:
            throw new DukeInvalidCommandException("I'm sorry, but I don't know what that means :-(");
        }

    }
}
