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
     * @param query The query of the user.
     * @return The appropriate Command.
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
        case "todo":
            return new AddTaskCommand(TaskType.TASK_TODO, arguments);

        case "deadline":
            return new AddTaskCommand(TaskType.TASK_DEADLINE, arguments);

        case "event":
            return new AddTaskCommand(TaskType.TASK_EVENT, arguments);

        case "done":
            return new MarkCompletedTaskCommand(arguments);

        case "delete":
            return new DeleteCommand(arguments);

        case "find":
            return new FilterTasksCommand(arguments);

        case "list":
            if (parts.length != 1) {
                throw new DukeInvalidCommandException("I'm sorry, but I don't know what that means :-(");
            }
            return new ListCommand();

        case "bye":
            if (parts.length != 1) {
                throw new DukeInvalidCommandException("I'm sorry, but I don't know what that means :-(");
            }
            return new TerminateSessionCommand();

        default:
            throw new DukeInvalidCommandException("I'm sorry, but I don't know what that means :-(");
        }


    }
}
