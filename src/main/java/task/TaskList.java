package task;

import exception.InvalidIndexException;

import java.util.ArrayList;

/**
 * TaskList Class.
 *
 * <p>Represents a list of Tasks.
 *
 * @author Marcus Ong
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> toList() {
        return tasks;
    }

    /**
     * Marks a task as done.
     *
     * @param taskIndex Index of task to set as done.
     * @return Task set as done.
     * @throws InvalidIndexException Index is out-of-bounds.
     */
    public Task done(int taskIndex) throws InvalidIndexException {
        try {
            Task task = tasks.get(taskIndex - 1);
            task.setDone(true);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("OOPS!!! I can't do that, please gimme a valid task ID mate.");
        }
    }

    /**
     * Deletes a task from the list permanently.
     *
     * @param taskIndex Index of task to delete.
     * @return Task that was deleted.
     * @throws InvalidIndexException Index is out-of-bounds.
     */
    public Task delete(int taskIndex) throws InvalidIndexException {
        try {
            Task task = tasks.remove(taskIndex - 1);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("OOPS!!! I can't do that, please gimme a valid task ID mate.");
        }
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Finds all tasks in list which contains searchString in its description.
     *
     * @param searchString Search string to match in a Task description.
     * @return TaskList containing Tasks which contains searchString in their description.
     */
    public TaskList find(String searchString) {
        TaskList matchingTasks = new TaskList();
        for (Task t : tasks) {
            if (t.getDescription().toLowerCase().contains(searchString.toLowerCase())) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }
}
