package tasks;

import tasks.Task;

import java.util.ArrayList;

/**
 * Represents list of tasks stored by the program.
 */
public class TaskList {
    private ArrayList<Task> commandList;

    /**
     * Constructor for TaskList Object
     *
     * @param commandList list of tasks.
     */
    public TaskList(ArrayList<Task> commandList) {
        this.commandList = commandList;
    }

    /**
     * return list of tasks.
     *
     * @return commandList.
     */
    public ArrayList<Task> getCommandList() {
        return commandList;
    }

    /**
     * Removes a task from tasklist.
     *
     * @param number index of task to be deleted.
     */
    public void deleteTask(int number) {
        assert (this.getCommandList().size() >= 1) : "cannot delete from empty task list";
        this.getCommandList().remove(number - 1);
    }

    /**
     * Adds task to the tasklist.
     *
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        this.commandList.add(task);
        assert (this.commandList.size() >= 1) : "task has not been successfully added";
    }
}
