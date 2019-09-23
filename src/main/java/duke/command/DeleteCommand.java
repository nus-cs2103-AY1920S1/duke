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

    private TaskList taskList;
    private Task toBeDeleted;
    private String errorMessage = "";
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
        try {
            this.indexToDelete = Integer.parseInt(message);
        } catch (NumberFormatException error) {
            errorMessage = "Wrong format! Did you mean to use the mass delete function instead?";
        }
    }

    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        this.taskList = listOfTasks;
        if (indexToDelete > taskList.size() || indexToDelete <= 0) {
            if (errorMessage.equals("")) {
                errorMessage = "Such task does not exist!";
                return;
            } else {
                return;
            }
        }
        toBeDeleted = listOfTasks.get(indexToDelete - 1);
        listOfTasks.removeTask(toBeDeleted);
        storage.updateTaskList(listOfTasks.getTasks());
        storage.writeToFile();
    }

    @Override
    public String toString() {
        if (!errorMessage.equals("")) {
            return errorMessage;
        } else {
            return "Done! I have deleted this task :\n\n" + toBeDeleted.toString();
        }
    }
}
