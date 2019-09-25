package duke.command;

import duke.Ui;
import duke.data.DukeData;
import duke.data.TaskList;

import java.io.IOException;

/**
 * The DeleteCommand handles any command from the
 * user to delete any task from the list.
 */
public class DeleteCommand implements Command {
    private int taskIndex;

    /**
     * Creates a new Delete Command with the given taskIndex.
     * @param taskIndex the index number of the task to be deleted
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Execute method which calls the method upon
     * initialisation of the object.
     * @param dukeData the storage object of the program
     * @param ui       ui object which handles output of user interaction
     * @param taskList the list of tasks that is stored in the Duke program
     * @return a string representation of the output for the delete command
     * @throws IOException if an I/O error occurs
     */
    @Override
    public String execute(DukeData dukeData, Ui ui, TaskList taskList)
            throws IOException {
        String deleted = ui.showDelete(this.taskIndex, taskList);
        taskList.removeTask(this.taskIndex);
        dukeData.update(taskList); // taskList removed deleted task
        return deleted;
    }
}
