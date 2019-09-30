package duke.command;

import duke.Duke;
import duke.exception.MissingParameterException;

import java.util.Arrays;

/**
 * The class that handles all the methods involving deleting a task.
 */

public class DeleteCommand extends Command {
    /**
     * Public constructor for DeleteCommand.
     * @param parameters Information needed to execute the command (i.e. the index of the task to be deleted).
     */
    public DeleteCommand(String parameters) {
        super(parameters);
    }

    /**
     * Executes the Delete command (i.e. deletes the task from the task list).
     * @return Duke's response during/after deleting the task.
     * @throws MissingParameterException if there are missing parameters.
     */
    public String executeCommand() throws MissingParameterException {
        if (parameters.length() <= 0) {
            throw new MissingParameterException(">:( !!! Please specify the index of the task to be deleted.");
        }

        try {
            String[] paramArr = parameters.split(" ");
            int[] intArr = Arrays.stream(paramArr).mapToInt(x -> Integer.parseInt(x)).toArray();
            return Duke.taskList.delete(intArr);

        } catch (ClassCastException e) {
            return ">:( !!! Please enter integers only.";

        } catch (NumberFormatException e) {
            return ">:( !!! Please enter integers only.";
        }
    }
}
