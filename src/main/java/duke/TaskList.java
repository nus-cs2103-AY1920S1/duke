package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null;
        this.list = tasks;
    }

    /**
     * Adds a new task to the list of tasks.
     *
     * @param task task to be added
     */
    public void add(Task task) {
        assert task != null;
        list.add(task);
    }

    /**
     * Deletes an existing task from the list of tasks.
     *
     * @param taskNum index of task to be deleted
     */
    public Task delete(int taskNum) {
        Task task = list.remove(taskNum - 1);
        return task;
    }

    /**
     * Adds a new task to the list of tasks.
     *
     * @return size of list
     */
    public int size() {
        return list.size();
    }

    /**
     * Get TaskList as an ArrayList (for printing).
     *
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getTaskArrayList() {
        return this.list;
    }

    /**
     * Sets task as done.
     *
     * @param taskNum index of task number
     * @return task with updated status
     */
    public Task setDone(int taskNum) {
        Task task = list.get(taskNum - 1);
        task.setAsDone();
        return task;
    }

    /**
     * Searches TaskList to find if any of the tasks matches the keyword.
     *
     * @param keyword keyword to be searched from user's input
     * @return list of tasks that contain the keyword or DukeException if no matches are found
     */
    public ArrayList<Task> findMatch(String keyword) throws DukeException {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : list) {
            if (task.getDesc().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            throw new DukeException("There are no matching tasks found!");
        } else {
            return matchingTasks;
        }
    }
}
