package duke.command;

import duke.storage.Serializer;
import duke.storage.Storage;
import duke.task.InvalidTaskDukeException;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a command instructing Duke to save tasks onto the hard-disk
 */
public class SaveCommand extends Command {

    /**
     * Executes the command and saves tasks into a file on the hard-disk.
     * @param tasks List of tasks
     * @param ui User-Interface
     * @param storage Storage object
     * @return
     * @throws InvalidTaskDukeException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidTaskDukeException {
        try {
            ArrayList<Task> taskList = tasks.getTasks();
            ArrayList<String> stringTasks = new ArrayList<>();
            Serializer serializer = new Serializer();
            for (Task t : taskList) {
                String serializedTask = serializer.serializeTask(t);
                stringTasks.add(serializedTask);
            }
            storage.saveTasks(stringTasks);
            return "Tasks saved. Bye bye!";
        } catch (IOException e) {
            return "Unable to save tasks. Please check your file path.";
        }
    }

    /**
     * Checks if the command is an exit command.
     * @return False.
     */
    public boolean checkExit() {
        return false;
    }

}
