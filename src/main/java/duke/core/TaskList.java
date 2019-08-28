package duke.core;

import duke.task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    ArrayList<Task> getList() {
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




