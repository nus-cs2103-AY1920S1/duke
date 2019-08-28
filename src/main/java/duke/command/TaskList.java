package duke.command;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> list;
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task task) {
        list.add(task);
    }

    public Task delete(String numString) {
        int numInt = Integer.valueOf(numString);
        return list.remove(numInt - 1);
    }
}
