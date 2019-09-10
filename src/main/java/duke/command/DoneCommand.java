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
        this.index = Integer.parseInt(message);
    }

    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        this.taskList = listOfTasks;
        listOfTasks.get(index - 1).completeTask();
        this.task = listOfTasks.get(index - 1);
        storage.updateTaskList(listOfTasks.getTasks());
        storage.writeToFile();
    }

    public String toString() {
        if (index > taskList.size() || index <= 0) {
            return "Such task does not exist!";
        } else {
            return task.toString();
        }
    }
}
