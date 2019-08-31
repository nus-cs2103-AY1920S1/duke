package task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task remove(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    public Task done(int index) {
        Task task = tasks.get(index);
        task.markDone();
        return task;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void list() {
        int counter = 1;

        //list out all the texts from the user
        for (Task t: tasks) {
            System.out.println("\t" + counter + "." + t.toString());
            counter++;
        }
    }

    public void list(ArrayList<Task> taskList) {
        int counter = 1;

        //list according to given list
        for (Task t: taskList) {
            System.out.println("\t" + counter + "." + t.toString());
            counter++;
        }
    }

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> taskList = new ArrayList<>();
        for (Task task: tasks) {
            if (task.getDescription().contains(keyword)) {
                taskList.add(task);
            }
        }
        return taskList;
    }

    public int getSize() {
        return tasks.size();
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }
}