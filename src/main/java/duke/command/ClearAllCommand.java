package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.UI;

import java.util.ArrayList;

public class ClearAllCommand extends Command {

    public ClearAllCommand(String message) {
        super(message);
    }

    /**
     * Used to execute the command and modify the list of tasks accordingly.
     *
     * @param listOfTasks List of tasks to be modified according to the input.
     * @param storage     Used to modify the files in the hard drive.
     * @param ui          Prints out all the messages.
     * @throws Exception For when there are any errors when executing the method.
     */
    @Override
    public void execute(TaskList listOfTasks, Storage storage, UI ui) throws Exception {
        for (int i = listOfTasks.size() - 1; i >= 0; i--) {
            listOfTasks.removeTask(listOfTasks.get(i));
        }
        storage.updateTaskList(listOfTasks.getTasks());
        storage.writeToFile();
    }

    @Override
    public String toString() {
        return "Congratulations! The list is now empty!";
    }
}
