package execution.commands;

import exception.DukeException;
import execution.Storage;
import execution.TaskList;
import execution.UI;
import models.Task;

import java.util.ArrayList;

/**
 * Represents the characteristics of a FindCommand object.
 */
public class FindCommand extends Command {
    /**
     * Constructs a FindCommand object with the input string with matching keywords.
     *
     * @param description the matching keyword that you are finding.
     */
    public FindCommand(String description) {

        super(description);

    }

    /**
     * Executes a FindCommand object. In this case, a new arraylist would be created with all the tasks that have
     * matching descriptions. Thereafter, the list of tasks would be printed by duke.
     *
     * @param taskList to retrieve the current tasks in the arraylist.
     * @param ui to set a response from duke.
     * @param storage to store any changes in the storage.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException {

        super.execute(taskList, ui, storage);
        checkValidity();

        ArrayList<Task> newList = new ArrayList<>();
        for (int i = 0; i < taskList.getList().size(); i++) {
            if (taskList.getList().get(i).contains(this.descriptionOfTask)) {
                newList.add(taskList.getList().get(i));
            }
        }

        ui.listTasks(new TaskList(newList));
    }

    /**
     * Handles the error and checks if it is valid for execution.
     *
     * @throws DukeException if the description of the find command is empty.
     */
    @Override
    protected void checkValidity() throws DukeException {
        if (this.descriptionOfTask.isEmpty()) {
            throw new DukeException(" ☹ OOPS!!! The description of an delete cannot be empty.");
        }
    }
}
