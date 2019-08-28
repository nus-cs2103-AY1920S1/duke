package duke.command;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list =  new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public Task removeTask(int i) {
        return list.remove(i - 1);
    }

    public int getSize() {
        return list.size();
    }


    public Task markTaskAsDone(int i) {
        Task task = list.get(i - 1);
        task.markAsDone();
        return task;
    }
}
