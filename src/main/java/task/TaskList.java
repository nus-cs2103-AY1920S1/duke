package task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }


    /**
     * add a task to the taskList
     * @param task task
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list");
    }


    /**
     * delete a task from the taskList
     * @param task
     */
    public void deleteTask(Task task) {
        tasks.remove(task);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(" " + task.toString());
    }


    /** delete a task according to index
     * @param index index of task
     */
    public void deleteTask(int index) {
        System.out.println("Noted. I've removed this task: ");
        printTask(index);
        tasks.remove(index);
    }


    private void printTask(int index) {
        System.out.println(" " + tasks.get(index).toString());
    }

}
