package duke.task;

import java.util.ArrayList;
import duke.exception.InvalidTaskIndexException;

/**
 * A Class that represents a list of all completed and uncompleted Tasks.
 */
public class TaskList {

    /**
     * Represents the list of takss
     */
    private static ArrayList<Task> tasks;

    /**
     * Constructs a new Task List which reads in 0 tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new Task List which reads in a list of tasks
     * @param tasks The loaded list of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds the ToDo Task based on the data given, and returns the index of the new task.
     * @param toDoData The name of the ToDo Task.
     * @return The index of the new task.
     */
    public int addTodoTask(String toDoData) {
        ToDoTask newTask = new ToDoTask(toDoData);
        tasks.add(newTask);

        int index = tasks.size() - 1;
        return index;
    }

    /**
     * Adds the Deadline Task based on the data given, and returns the index of the new task.
     * @param taskName The name of the Deadline Task.
     * @param taskDate The date of the Deadline Task.
     * @return The index of the new task.
     */
    public int addDeadlineTask(String taskName, String taskDate) {
        DeadlineTask newTask = new DeadlineTask(taskName, taskDate);
        tasks.add(newTask);

        int index = tasks.size() - 1;
        return index;
    }

    /**
     * Adds the Event Task based on the data given, and returns the index of the new task.
     * @param taskName The name of the Deadline Task.
     * @param taskDate The date of the Deadline Task.
     * @return The index of the new task.
     */
    public int addEventTask(String taskName, String taskDate) {
        EventTask newTask = new EventTask(taskName, taskDate);
        tasks.add(newTask);

        int index = tasks.size() - 1;
        return index;
    }

    /**
     * Completes a task based on the index given.
     * @param index The index of the task.
     * @throws InvalidTaskIndexException If the index is lees than 0 or the index exceeds the number of tasks.
     */
    public void completeTask(int index) throws InvalidTaskIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskIndexException();
        } else {
            Task task = tasks.get(index);
            task.isCompleted = true;
        }
    }

    /**
     * Deletes a task based on the index given, returning the Task that was deleted.
     * @param index The index of the task.
     * @return The deleted task.
     * @throws InvalidTaskIndexException If the index is less than 0 or the index exceeds the number of tasks.
     */
    public Task deleteTask(int index) throws InvalidTaskIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskIndexException();
        } else {
            Task task = tasks.get(index);
            tasks.remove(index);
            return task;
        }
    }

    /**
     * Returns the number of tasks in the list.
     * @return The number of tasks in the list.
     */
    public static int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Returns the task at the index provided.
     * @param index The index of the task.
     * @return The task to be retrieved.
     */
    public static Task getTask(int index) {
        return tasks.get(index);
    }
}
