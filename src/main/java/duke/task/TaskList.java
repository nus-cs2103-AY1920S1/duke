package duke.task;


import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int size;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.size = 0;
    }

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    public int getSize() {
        return size;
    }

    public Task getTaskByIndex(int index) {
        return tasks.get(index);
    }

    public Task deleteTaskByIndex(int index) {
        size--;
        return tasks.remove(index);
    }

    public void addTask(Task task) {
        size++;
        tasks.add(task);
    }
}
