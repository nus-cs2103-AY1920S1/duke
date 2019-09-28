/**
 * Class to represent the arraylist containing the tasks.
 */

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> list) {
        this.taskList = list;
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Method to find a task given a description in the task list.
     * @param description keyword to find the task
     * @return returns the task with given keyword
     * @throws TaskNotFoundException no task with given keyword is found
     */
    public Task findTask(String description) throws TaskNotFoundException {
        Task t = new Task("");
        boolean flag = true;
        for (Task task : taskList) {
            if (task.getDescription().contains(description)) {
                t = task;
                flag = false;
            }
        }
        if (flag) {
            throw new TaskNotFoundException("Task not found.");
        }
        return t;
    }
}
