package duke.parser;

import duke.command.DoneCommand;
import duke.command.*;
import duke.exception.DukeException;
import duke.task.Task;

public class Parser {

    public static Command parse(String[] command) throws DukeException {
        try {
            CommandList commandCode = CommandList.valueOf(command[0].toUpperCase());
            String details = command[1];
            Task task;
            switch (commandCode) {
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListTasksCommand();
            case TODO:
                task = CreateCommand.createTodo(details);
                return AddCommand.createAddCommand(task);
            case DEADLINE:
                task = CreateCommand.createDeadline(details);
                return AddCommand.createAddCommand(task);
            case EVENT:
                task = CreateCommand.createEvent(details);
                return AddCommand.createAddCommand(task);
            case DONE:
                return new DoneCommand(details);
            case DELETE:
                return new DeleteCommand(details);
            default:
                //return new InvalidCommand();
                throw new DukeException("D:  OOPS!!! I'm sorry, but I don't know what that means. "
                                + "I sure need more sleep...");
            } // End switch
        } catch (IllegalArgumentException e) {
            throw new DukeException("D:  OOPS!!! I'm sorry, but I don't know what that means. "
                    + "I sure need more sleep...");
        } // End try-catch.
    }
}
