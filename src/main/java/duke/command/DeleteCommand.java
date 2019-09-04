package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Class that represents the command to delete a task.
 */
public class DeleteCommand extends Command {

    /**
     * Index of the file that is to be deleted from the list of tasks.
     */
    private int indexToDelete;

    /**
     * Constructor that takes the index of the task to be deleted.
     * @param message String of the index of the task to be deleted.
     */
    public DeleteCommand(String message) {
        super(message);
        this.indexToDelete = Integer.parseInt(message);
    }

    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        if (indexToDelete > listOfTasks.size() || indexToDelete <= 0) {
            throw new DukeException("     Such task does not exist!");
        }
        Task toBeDeleted = listOfTasks.get(indexToDelete - 1);
        listOfTasks.removeTask(toBeDeleted);
        storage.updateTaskList(listOfTasks.getTasks());
        storage.writeToFile();
        ui.printTaskDelete(toBeDeleted);
    }

}
