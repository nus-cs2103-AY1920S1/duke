package duke.command;

import duke.Duke;
import duke.exception.MissingParameterException;
/**
 * The class handles methods involving finding a task.
 */

public class FindCommand extends Command {
    /**
     * Public constructor for FindCommand.
     * @param parameters Information needed to execute the command (i.e. the String that the user is searching for).
     */
    public FindCommand(String parameters) {
        super(parameters);
    }

    /**
     * Executes the Find command (i.e. finds the task containing the String the user is searching for).
     * @return The tasks containing the String the user is searching for.
     * @throws MissingParameterException if there are missing parameters in the command.
     */
    public String executeCommand() throws MissingParameterException {
        if (parameters.length() <= 0) {
            throw new MissingParameterException(">:( !!! Please provide a search query.");
        } else {
            return Duke.taskList.find(parameters);
        }
    }
}
