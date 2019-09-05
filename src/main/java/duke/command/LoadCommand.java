package duke.command;

import duke.exception.DukeException;
import duke.storage.Serializer;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Represents a command instructing Duke to load tasks from the hard-disk.
 */
public class LoadCommand extends Command {

    /**
     * Executes the command and loads the tasks from the hard-disk.
     * @param tasks List of tasks
     * @param ui User-Interface
     * @param storage Storage object
     * @return Duke's response.
     * @throws DukeException If tasks cannot be loaded from the hard-disk.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            ArrayList<String> stringTasks = storage.loadTasks();
            ArrayList<Task> taskList = new ArrayList<>();
            Serializer serializer = new Serializer();
            for (String s : stringTasks) {
                Task t = serializer.deserializeTask(s);
                taskList.add(t);
            }
            tasks.addAllTasks(taskList);
            return "Tasks Pre-Loaded";
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found.");
        }
    }

    /**
     * Checks if this command is an exit command.
     * @return False.
     */
    public boolean checkExit() {
        return false;
    }

}
