package duke.command;

import duke.Duke;
import duke.exception.MissingParameterException;
import duke.task.Task;
import duke.task.Todo;
/**
 * The class handles methods involving creating a Todo task.
 */

public class TodoCommand extends Command {

    /**
     * Public constructor for TodoCommand.
     * @param parameters Information needed to execute the command (i.e. description).
     */
    public TodoCommand(String parameters) {
        super(parameters);
    }

    /**
     * Executes the Todo command (i.e. creates the Todo task object)
     * @return Duke's response during/after creating the Todo task object.
     * @throws MissingParameterException if there are missing parameters in the command.
     */
    public String executeCommand() throws MissingParameterException {
        if (parameters.length() <= 0) {
            throw new MissingParameterException(">:( !!! The description of a todo cannot be empty.");

        } else {
            Task t = new Todo(parameters);
            return Duke.taskList.add(t);
        }
    }
}
