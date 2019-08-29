import Task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Gets the ArrayList from a TaskList object.
     *
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Adds a task to the TaskList
     *
     * @param task Task to be added
     */
    public void addTask(Task task) {
        list.add(task);
    }

    public Task deleteTask(String position) {
        int index = Integer.parseInt(position) - 1;
        Task currTask = list.get(index);
        list.remove(index);
        return currTask;
    }

    /**
     * Change the status of a task of position p in the TaskList
     * to "done" and returns the updated Task.
     *
     * @param position index + 1 of the task in the list.
     * @return Updated Task.
     */
    public Task doTask(String position) {
        int index = Integer.parseInt(position) - 1;
        Task currTask = list.get(index);
        currTask.doTask();
        return currTask;
    }


}
