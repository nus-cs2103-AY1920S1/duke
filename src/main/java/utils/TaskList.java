package utils;

import task.Task;
import java.util.ArrayList;

/**
 * Contains operations for commands to list.
 * */
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Updates task in list of given position under the Text File.
     *
     * @param num Position of task in tasklist/ text file
     * @return updated task of given position
     */
    public Task updateTask(int num) {
        Task currTask = tasks.get(num - 1);
        currTask.markAsDone();
        return currTask;
    }

    /**
     * Deletes task in list of given position.
     *
     * @param num Position of task in tasklist
     * @return deleted task of given position
     */
    public Task deleteTask(int num) {
        Task currTask = tasks.get(num - 1);
        tasks.remove(num - 1);
        return currTask;
    }

    /**
     * Searches for tasks in TaskList containing given string.
     *
     * @param command String to search for relevant task
     * @return list of task containing the given command
     */
    public ArrayList<Task> searchTasks(String command) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(command)) {
                results.add(task);
            } // else ignore
        }
        return results;
    }

}