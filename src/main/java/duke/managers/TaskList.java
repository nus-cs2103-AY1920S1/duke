/**
 * This class contains the task list and methods to manipulate the list of tasks: generating the number of tasks in
 * memory, adding tasks or deleting tasks.
 */

package duke.managers;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> allTasks;

    /**
     * Loads previously stored information in the hard disk.
     * @param loadedTasks a data structure containing the tasks stored
     * @throws DukeException is thrown when there are no tasks to be laoded
     */
    public TaskList(ArrayList<Task> loadedTasks) throws DukeException {
        if (loadedTasks.size() == 0) {
            throw new DukeException("No tasks to load. A new TaskList will be created.");
        } else {
            this.allTasks = loadedTasks;
        }
    }

    /**
     * Generates an empty list of tasks in the case when there are no previously saved tasks.
     */
    public TaskList() {
        allTasks = new ArrayList<>();
    }

    public ArrayList<Task> getAllTasks() {
        return allTasks;
    }

    public int totalNumTasks() {
        return allTasks.size();
    }

    public Task getTask(int taskNum) {
        return allTasks.get(taskNum - 1);
    }

    public Task delTask(int taskNum) {
        return allTasks.remove(taskNum - 1);
    }

    public void addTask(Task task) {
        allTasks.add(task);
    }
}
