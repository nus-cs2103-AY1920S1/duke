package duke.execution.commands;

import duke.exception.DukeException;
import duke.execution.Storage;
import duke.execution.TaskList;
import duke.execution.UI;

/**
 * Represents the attributes and behavior of a Command object.
 */
public abstract class Command {

    protected String descriptionOfTask;

    /**
     * Constructs the Command object with the description of task string value as parameter.
     *
     * @param descriptionOfTask to construct the command.
     */
    public Command(String descriptionOfTask) {

        this.descriptionOfTask = descriptionOfTask;

    }

    /**
     * Executes the command object and carries out its operations as per different type of command.
     * This is can be treated like an abstract method as it will be overridden based on the type of command.
     *
     * @param tasklist arraylist of tasks since a task would be created from this duke.execution of a command.
     * @param ui to set a response from duke.
     * @param storage to store any changes in the storage.
     * @throws DukeException if the user input is invalid.
     */
    public void execute(TaskList tasklist, UI ui, Storage storage) throws DukeException {

        checkValidity();

    }

    /**
     * Checks for any duke.exception in the duke.execution of the commands.
     *
     * @throws DukeException when there is an duke.exception during the duke.execution.
     */
    protected void checkValidity() throws DukeException {
        return;
    }
}
