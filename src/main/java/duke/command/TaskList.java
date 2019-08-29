package duke.command;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void add(Task task) {
        list.add(task);
    }

    Task delete(int index) {
        return list.remove(index);
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void setList(ArrayList<Task> list) {
        this.list = list;
    }
}
