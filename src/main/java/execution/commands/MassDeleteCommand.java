package execution.commands;

import exception.DukeException;
import execution.Storage;
import execution.TaskList;
import execution.UI;

import java.util.Arrays;
import java.util.Collections;

/**
 * Represents the characteristics of a Mass Delete Command.
 */
public class MassDeleteCommand extends Command {

    /**
     * Constructs a MassDeleteCommand object.
     *
     * @param commands which is a string of indexes of tasks to be deleted, with respect to the current tasklist.
     */
    public MassDeleteCommand(String commands) {

        super(commands);

    }

    /**
     * Execution of a MassDeleteCommand which will delete tasks off the current tasklist.
     *
     * @param taskList of current tasks.
     * @param ui to set a response from duke.
     * @param storage to store any changes in the storage.
     * @throws DukeException if the description is empty.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {

        super.execute(taskList, ui, storage);

        String[] arrayToBeDeleted = this.descriptionOfTask.trim().split(" ");
        Integer[] arrayInInt = new Integer[arrayToBeDeleted.length];

        //changing to integer array for sorting
        for (int i = 0; i < arrayToBeDeleted.length; i++) {
            arrayInInt[i] = Integer.parseInt(arrayToBeDeleted[i]);
        }

        // Sorts arr[] in descending order for deletion to avoid troubling with indexes.
        Arrays.sort(arrayInInt, Collections.reverseOrder());

        //delete the tasks using the DeleteCommand
        for (int j = 0; j < arrayInInt.length; j++) {

            Command deleting = new DeleteCommand(arrayInInt[j] + "");
            deleting.execute(taskList, ui, storage);

        }

    }

    /**
     * Handles the error and checks if it is valid for execution.
     *
     * @throws DukeException if description (the number) is left empty.
     */
    @Override
    protected void checkValidity() throws DukeException {
        if (this.descriptionOfTask.isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of a MassDelete cannot be empty.");
        }
    }
}

