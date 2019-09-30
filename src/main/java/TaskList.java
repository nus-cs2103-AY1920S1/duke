import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * This class contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {

    protected ArrayList<Task> list;

    public TaskList (ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Adds the task to the task list
     * @param task The Task
     */
    public void add (Task task) {
        list.add(task);
    }

    /**
     * Marks the task as done
     * @param index The Index of the Task
     * @return
     */
    public Task done (int index) {
        Task task = list.get(index);
        task.markAsDone();

        return task;
    }

    /**
     * Deletes a task
     * @param index The index of the task
     * @return The deleted task
     */
    public Task delete (int index) {

        Task task = list.remove(index);
        return task;
    }

    public Task[] find(String text) {

        ArrayList<Task> tasks =  new ArrayList<>();

        for (Task task : list) {
            if (task.description.toLowerCase().contains(text.toLowerCase())) {
                tasks.add(task);
            }
        }

        return tasks.toArray(new Task[tasks.size()]);
    }
}
