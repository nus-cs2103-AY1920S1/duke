package main;

import task.Task;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Used to store tasks
 */
public class TaskList implements Iterable<Task>{

    private ArrayList<Task> tasks;

    /**
     * Creates a new TaskList object with no existing tasks
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new TaskList object with existing tasks
     * @param existingTaskList ArrayList of existing tasks
     */
    public TaskList(ArrayList<Task> existingTaskList) {
        this.tasks = existingTaskList;
    }

    /**
     * Returns the Tasks stored in an ArrayList
     * @return The task list in ArrayList form.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds a new task to the task list
     * @param newTask Task object to be added
     * @return The task that has been added
     */
    public Task addTask(Task newTask) {
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Deletes a task from the task list given by its task ID (starting from 1)
     * @param taskNum task ID (index starts from 1)
     * @return The deleted task with specified task ID.
     */
    public Task deleteTask(int taskNum) {
        Task removedTask = this.tasks.remove(taskNum-1);
        return removedTask;
    }

    /**
     * Marks a task given by its task ID as completed.
     * @param taskNum The task ID of the task to be mark as completed.
     * @return The updated Task object, with completion status updated.
     */
    public Task finishTask(int taskNum) {
        Task completedTask = this.tasks.get(taskNum-1);
        completedTask.finishTask();
        return completedTask;
    }

    /**
     * Returns the current size of the task list
     * @return the current size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task with the corresponding task ID.
     * @param taskID is the ACTUAL task ID, with index starting from 1
     * @return Task Object with the corresponding task ID.
     */
    public Task getTask(int taskID) {
        return tasks.get(taskID-1);
    }

    /**
     * Remove all tasks from the existing task list.
     */
    public void clearAll() {
        this.tasks.clear();
    }

    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> tasksFound = new ArrayList<>();
        for (Task task : tasks) {
            if (task.containsKeyword(keyword)) {
                tasksFound.add(task);
            }
        }
        return tasksFound;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Task> iterator() {

        Iterator<Task> iter = new Iterator<>() {

            private int currIdx = 0;

            @Override
            public boolean hasNext() {
                return currIdx < tasks.size() && tasks.get(currIdx) != null;
            }

            @Override
            public Task next() {
                return tasks.get(currIdx++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return iter;
    }
    /*
    Note to self: the out of bounds exception should be caught earlier under the part where you process command!
     */
}
