package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

/**
 * Class that represent the command of finishing a task.
 */
public class DoneCommand extends Command {

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
        if (index > listOfTasks.size() || index <= 0) {
            throw new DukeException("     Such task does not exist!");
        }
        listOfTasks.get(index - 1).completeTask();
        storage.updateTaskList(listOfTasks.getTasks());
        storage.writeToFile();
        ui.printTaskDone(listOfTasks.get(index - 1));
    }
}
