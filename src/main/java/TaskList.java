package duke.command;

import duke.task.Task;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) throws FileNotFoundException {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public Task deleteTask(int index) {
        return list.remove(index);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public void setTaskList(ArrayList<Task> list) {
        this.list = list;
    }
}

