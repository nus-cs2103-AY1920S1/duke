package duke;

import duke.task.Task;
import java.util.ArrayList;

/**
 * Contains the app's task list.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Instantiates a new Task ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Sets this object's ArrayList to an existing Task ArrayList.
     * @param tasks An existing Task ArrayList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the size of the TaskList.
     * @return The size of the TaskList as an int.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets a Task from the TaskList by its position in the list.
     * @param taskNumber The Task's position in the list (1-indexed).
     * @return The Task at the given position.
     */
    public Task get(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    /**
     * Adds a Task to the end of the TaskList.
     * @param task The Task to be added to the TaskList.
     * @return True if the Task is successfully added. False otherwise.
     */
    public boolean add(Task task) {
        return tasks.add(task);
    }

    /**
     * Removes a Task at the given position from the TaskList.
     * @param taskNumber The position (1-indexed) of the Task to be removed.
     * @return The Task that has been removed from the TaskList.
     */
    public Task remove(int taskNumber) {
        return tasks.remove(taskNumber - 1);
    }
}
