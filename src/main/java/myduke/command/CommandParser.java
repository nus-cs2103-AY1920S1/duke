package myduke.command;

import myduke.exception.DukeException;
import myduke.exception.DukeInvalidCommandException;
import myduke.type.TaskType;

public class CommandParser {
    public static Command create(String query) throws DukeException {

        String[] parts = query.trim().split(" ", 2);
        if (parts.length == 0 || parts[0].length() == 0) {
            throw new DukeException("Query should not be empty");
        }

        //Try to parse User's query
        switch (parts[0]) {
        case "todo":
            return new AddTaskCommand(TaskType.TASK_TODO, parts[1]);

        case "deadline":
            return new AddTaskCommand(TaskType.TASK_DEADLINE, parts[1]);

        case "event":
            return new AddTaskCommand(TaskType.TASK_EVENT, parts[1]);

        case "done":
            return new MarkCompletedTaskCommand(parts[1]);

        case "delete":
            return new DeleteCommand(parts[1]);

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
