package duke;

import duke.task.Task;
import duke.task.TaskList;
import java.util.List;

import java.io.IOException;

public interface Storage {
    /**
     * Loads tasks from external storage to a new list, then returns that list.
     *
     * @return                  List of tasks that were loaded from file
     * @throws DukeException    If tasks cannot be loaded from file
     */
    public List<Task> load() throws DukeException;

    /**
     * Writes the tasks in the given list into external storage.
     *
     * @param tasks             List of tasks to be written.
     * @throws IOException      If file cannot be found, etc.
     */
    public void writeToFile(TaskList tasks) throws IOException;
}
