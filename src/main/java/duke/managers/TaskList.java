package duke.managers;
import duke.tasks.*;
import duke.exceptions.DukeException;
import java.util.ArrayList;
/*
The class contains the task list.
 */
public class TaskList {
    private static ArrayList<Task> allTasks;

    //constructor one for when loaded from hard disk
    public TaskList(ArrayList<Task> loadedTasks) throws DukeException {
        if (loadedTasks.size() == 0) {
            throw new DukeException("No tasks to load. A new TaskList will be created.");
        } else {
            this.allTasks = loadedTasks;
        }
    }

    //constructor two for when generating new task list (when there is nothing to be loaded from the hard disk)
    public TaskList() {
        allTasks = new ArrayList<>();
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
