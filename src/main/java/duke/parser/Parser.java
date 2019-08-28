package duke.parser;

import duke.DukeException;
import duke.command.*;
import duke.task.TaskList;

public class Parser {

    private static final String ERROR_INVALID_INPUT = "I'm sorry, but I don't know what that means :-(";
    private static final String ERROR_MISSING_DESCRIPTION = "The description cannot be empty.";
    private static final String ERROR_MISSING_TASK_ID = "The id of the task must be provided.";
    private static final String ERROR_INVALID_TASK_ID = "The id of the task must be a number. e.g. done 1";
    private static final String ERROR_TOO_MANY_ARGUMENTS = "There are too many arguments for this command.";

    public static Command parse(String fullCommand) throws DukeException {
        String[] line = fullCommand.split(" ", 2);
        switch (line[0]) {
        case "todo":
        case "deadline":
        case "event":
            if (line.length == 1) {
                throw new DukeException(ERROR_MISSING_DESCRIPTION);
            }
            return new AddCommand(line[0], line[1]);
        case "list":
            if (line.length != 1) {
                throw new DukeException(ERROR_TOO_MANY_ARGUMENTS);
            }
            return new ListCommand();
        case "find":
            if (line.length == 1) {
                throw new DukeException(ERROR_MISSING_DESCRIPTION);
            }
            return new FindCommand(line[1]);
        case "done":
            return new DoneCommand(getId(line));
        case "delete":
            return new DeleteCommand(getId(line));
        case "bye":
            if (line.length != 1) {
                throw new DukeException(ERROR_TOO_MANY_ARGUMENTS);
            }
            return new ExitCommand();
        default:
            throw new DukeException(ERROR_INVALID_INPUT);
        }
    }

    /**
     * Returns parsed number from input.
     *
     * @param input Entire line of input.
     * @return Number in rest of input.
     * @throws DukeException If rest of input is not a number.
     */
    private static int getId(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException(ERROR_MISSING_TASK_ID);
        }
        int taskId;
        try {
            taskId = Integer.parseInt(input[1]);
        } catch (NumberFormatException ex) {
            throw new DukeException(ERROR_INVALID_TASK_ID);
        }
        return taskId;

    }
}
