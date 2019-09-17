package duke.tasklist;

import duke.task.Task;

import java.util.LinkedList;

/**
 * Holds a universal list of tasks.
 */
public class TaskList extends LinkedList<Task> {
    public static LinkedList<Task> taskList;

    /**
     * Constructor.
     */
    public TaskList() {
        taskList = new LinkedList<>();
    }

    /**
     * Constructor.
     * @param tasks List of tasks
     */
    public TaskList(LinkedList<Task> tasks) {
        taskList = new LinkedList<>(tasks);
    }

    @Override
    public int size() {
        return taskList.size();
    }

    @Override
    public Task get(int n) {
        return taskList.get(n);
    }

    public LinkedList getList() {
        return this.taskList;
    }

    public void setList(LinkedList<Task> taskList) {
        this.taskList = taskList;
    }
}
