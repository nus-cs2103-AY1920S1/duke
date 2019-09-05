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

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list");
    }

    public void deleteTask(Task task) {
        tasks.remove(task);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(" " + task.toString());
    }

    public void deleteTask(int index) {
        System.out.println("Noted. I've removed this task: ");
        printTask(index);
        tasks.remove(index);
    }


    private void printTask(int index) {
        System.out.println(" " + tasks.get(index).toString());
    }

}
