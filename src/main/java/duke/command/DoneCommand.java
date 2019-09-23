package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;
import duke.task.Task;

/**
 * Class that represent the command of finishing a task.
 */
public class DoneCommand extends Command {

    private String errorMessage = "";
    private Task task;
    private TaskList taskList;
    /**
     * Index of the task to be completed.
     */
    private int index;

    /**
     * Constructor that takes the index of the task to be completed.
     * @param message String of the index of the task to be completed.
     */
    public DoneCommand(String message) {
        super(message);
        try {
            this.index = Integer.parseInt(message);
        } catch (NumberFormatException error) {
            errorMessage = "Sorry that is not an acceptable number format!";
        }
    }

    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        this.taskList = listOfTasks;
        if (!errorMessage.equals("")) {
            return;
        }
        if (index > taskList.size() || index <= 0) {
            errorMessage = "Such task does not exist!";
            return;
        }
        listOfTasks.get(index - 1).completeTask();
        this.task = listOfTasks.get(index - 1);
        storage.updateTaskList(listOfTasks.getTasks());
        storage.writeToFile();
    }

    @Override
    public String toString() {
        if (!errorMessage.equals("")) {
            return errorMessage;
        } else {
            String output = "";
            output += "Well done! You have completed this task:\n\n"
                    + task.toString();
            return output;
        }
    }
}
