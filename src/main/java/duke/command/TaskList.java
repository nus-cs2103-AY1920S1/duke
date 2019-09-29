package duke.command;

import duke.task.Task;

import java.util.ArrayList;

/**
 *  Contains the task list. It has operations to add/delete tasks in the list.
 */
public class TaskList {
    public ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Updates the list of tasks.
     *
     * @param task task being added.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Updates the list of tasks.
     *
     * @param numString position of task being deleted.
     */
    public Task delete(String numString) {
        int numInt = Integer.valueOf(numString);
        return list.remove(numInt - 1);
    }

    public int getSize() {
        return list.size();
    }
}
