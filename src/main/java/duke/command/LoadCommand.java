package duke.command;

import duke.date.InvalidDateDukeException;
import duke.exception.DukeException;
import duke.storage.Serializer;
import duke.storage.Storage;
import duke.task.InvalidTaskDukeException;
import duke.task.Task;
import duke.tasklist.TaskList;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Represents a command instructing Duke to load tasks from the hard-disk.
 */
public class LoadCommand extends Command {

    private static final String LOAD_SUCCESS_MESSAGE = "Tasks pre-loaded";

    /**
     * Executes the command and loads the tasks from the hard-disk.
     * @param tasks List of tasks
     * @param storage Storage object
     * @return Duke's response.
     * @throws DukeException If tasks cannot be loaded from the hard-disk.
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            ArrayList<Task> taskList = loadAndDeserializeTasks(storage);
            tasks.addAllTasks(taskList);
            return LOAD_SUCCESS_MESSAGE;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found.");
        }
    }

    private ArrayList<Task> loadAndDeserializeTasks(Storage storage) throws FileNotFoundException,
            InvalidDateDukeException, InvalidTaskDukeException {
        ArrayList<String> stringTasks = storage.loadTasks();
        ArrayList<Task> taskList = new ArrayList<>();
        Serializer serializer = new Serializer();
        for (String s : stringTasks) {
            Task t = serializer.deserializeTask(s);
            taskList.add(t);
        }
        return taskList;
    }

    /**
     * Checks if this command is an exit command.
     * @return False.
     */
    public boolean checkExit() {
        return false;
    }

}
