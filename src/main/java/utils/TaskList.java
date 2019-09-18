package utils;

import java.util.ArrayList;

import tasks.Task;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void addTask(Task t) {
        taskList.add(t);
    }

    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns a list of tasks, based on description provided.
     *
     * @param description a description of item to be found
     * @return
     */
    public ArrayList<Task> findTasks(String description) {

        ArrayList<Task> foundList = new ArrayList<>();

        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            if (t.getDescription().contains(description)) {
                foundList.add(t);
            }
        }

        return foundList;
    }
}