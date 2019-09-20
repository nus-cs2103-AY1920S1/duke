package duke.parser;

import duke.DukeException;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;

public class Parser {

    private static final String ERROR_MISSING_DESCRIPTION = "The description cannot be empty.";
    private static final String ERROR_MISSING_TASK_ID = "The id of the task must be provided.";
    private static final String ERROR_INVALID_TASK_ID = "The id of the task must be a number. e.g. done 1";
    private static final String ERROR_TOO_MANY_ARGUMENTS = "There are too many arguments for this command.";

    /**
     * Returns a Command object depending on the command inputted by the User.
     *
     * @param fullCommand User input.
     * @return Command object depending on given input.
     * @throws DukeException If input is not in the right format.
     */
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
            return new DoneCommand(getIds(line));
        case "delete":
            return new DeleteCommand(getIds(line));
        case "help":
            if (line.length != 1) {
                throw new DukeException(ERROR_TOO_MANY_ARGUMENTS);
            }
            return new HelpCommand();
        case "bye":
            if (line.length != 1) {
                throw new DukeException(ERROR_TOO_MANY_ARGUMENTS);
            }
            return new ExitCommand();
        default:
            throw new DukeException();
        }
    }

    /**
     * Returns parsed number from input.
     *
     * @param input Entire line of input.
     * @return Number in rest of input.
     * @throws DukeException If rest of input is not a number.
     */
    private static int[] getIds(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException(ERROR_MISSING_TASK_ID);
        }
        String[] ids = input[1].split(" ");
        int[] taskIds = new int[ids.length];
        try {
            for (int i = 0; i < taskIds.length; i++) {
                taskIds[i] = Integer.parseInt(ids[i]);
            }
        } catch (NumberFormatException ex) {
            throw new DukeException(ERROR_INVALID_TASK_ID);
        }
        return taskIds;
    }
}
