package duke.command;

import duke.storage.Serializer;
import duke.storage.Storage;
import duke.task.InvalidTaskDukeException;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a command instructing Duke to save tasks onto the hard-disk.
 */
public class SaveCommand extends Command {

    private static final String SAVE_SUCCESS_MESSAGE = "Tasks saved successfully!";
    private static final String SAVE_FAIL_MESSAGE = "Unable to save tasks. Please check your file path.";

    /**
     * Executes the command and saves tasks into a file on the hard-disk.
     * @param tasks List of tasks
     * @param storage Storage object
     * @return Success or fail message.
     * @throws InvalidTaskDukeException If the tasks are stored incorrectly.
     */
    public String execute(TaskList tasks, Storage storage) throws InvalidTaskDukeException {
        try {
            ArrayList<String> stringTasks = serializeAndSaveTasks(tasks);
            storage.saveTasks(stringTasks);
            return SAVE_SUCCESS_MESSAGE;
        } catch (IOException e) {
            return SAVE_FAIL_MESSAGE;
        }
    }

    private ArrayList<String> serializeAndSaveTasks(TaskList tasks) throws InvalidTaskDukeException {
        ArrayList<Task> taskList = tasks.getTasks();
        ArrayList<String> stringTasks = new ArrayList<>();
        Serializer serializer = new Serializer();
        for (Task t : taskList) {
            String serializedTask = serializer.serializeTask(t);
            stringTasks.add(serializedTask);
        }
        return stringTasks;
    }

    /**
     * Checks if the command is an exit command.
     * @return False.
     */
    public boolean checkExit() {
        return false;
    }

}
