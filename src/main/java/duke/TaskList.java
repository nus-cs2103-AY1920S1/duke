package duke;

import task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    TaskList() {
        tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
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
        System.out.println("\tHere are the tasks in your list:");
        for (Task t: tasks) {
            System.out.println("\t" + counter + "." + t.toString());
            counter++;
        }
    }

    public int getSize() {
        return tasks.size();
    }

    ArrayList<Task> getAllTasks() {
        return tasks;
    }
}