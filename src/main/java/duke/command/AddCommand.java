package duke.command;

import duke.component.DukeException;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;
import duke.task.Task;

import java.io.IOException;

/**
 * Command Class for adding tasks
 */
public class AddCommand extends Command {

    private Task newTask;

    /**
     * Constructor for AddCommand Object
     * @param task task to be added
     */
    public AddCommand(Task task) {
        this.newTask = task;
    }

    /**
     * Executes the operation of adding task into the task list
     * @param taskList the list of tasks to be added to
     * @param storage the storage to store inside hard disk
     * @param ui the ui for user interaction
     * @return boolean indication of successful or unsuccessful running of command
     * @throws DukeException
     * @throws IOException
     */
    @Override
    public boolean executeCommand(TaskList taskList, Storage storage, Ui ui) throws DukeException, IOException {
        taskList.add(newTask);

        ui.printAddedAcknowledgement(newTask, taskList.getSize());

        storage.save(taskList);

        return true;
    }
}
