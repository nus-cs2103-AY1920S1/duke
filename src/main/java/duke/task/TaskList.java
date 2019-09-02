package duke.task;

import java.util.List;
import java.util.ArrayList;

/**
 * The TaskList class wraps a List of Tasks and provides a method to display
 * all tasks as a formatted list.
 */
public class TaskList {

    /** List of tasks. */
    private final List<Task> tasks;

    /** Creates a new, empty TaskList. */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates a new TaskList from the given List of Tasks.
     *
     * @param tasks     List of Tasks to be used in the new TaskList.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns number of tasks in the current list.
     *
     * @return  Size of TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the Task at the given index.
     *
     * @param index     Index of Task.
     * @return          Task at the given index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param newTask   Task to be added
     */
    public void add(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Removes a Task from the list.
     *
     * @param index     Index of Task to be removed.
     * @return          The removed Task.
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Checks whether the TaskList is empty.
     *
     * @return  True if the task list is empty and false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns a String representing all of the tasks as a formatted list.
     *
     * @return  String representation of task list.
     */
    @Override
    public String toString() {
        String output = "";
        for (Task task : tasks) {
            output = output.concat(task.formatAsData() + "\n");
        }
        return output;
    }
}
