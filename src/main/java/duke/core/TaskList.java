package duke.core;

import java.util.ArrayList;
import duke.task.Task;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void removeTask(int i) {
        tasks.remove(i);
    }

    public int getSize() {
        return tasks.size();
    }
}




