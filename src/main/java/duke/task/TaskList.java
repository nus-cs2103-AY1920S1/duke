package duke.task;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);

        System.out.println("Okay! I've added: " + task.getDescription()
            + ". Use list to see all your tasks!");
    }

//    public void addAll(ArrayList<Task> tasks) {
//        this.tasks.addAll(tasks);
//    }

    public void printList() {
        int i = 1;
        for (Task task : tasks) {
            String s = task.toString();
            System.out.println(i + ". " + s);
            i++;
        }
    }

    public Task getTask(int taskIndex) throws IndexOutOfBoundsException {
        return tasks.get(taskIndex - 1);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void deleteTask(Task taskToDelete) {
        String taskDescription = taskToDelete.toString();
        tasks.remove(taskToDelete);
        System.out.println("Noted. I've removed this task: " + taskDescription);
        System.out.println("Now you have " + tasks.size() + " items in this list.");
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public TaskList findTasks(String keyword) {
        TaskList matchingTasks = new TaskList();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

}
