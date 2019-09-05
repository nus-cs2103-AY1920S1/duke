package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> lst) {
        list = lst;
    }

    public int size() {
        return list.size();
    }

    public Task get(int index) {
        return list.get(index);
    }

    public void add(int index, Task t) {
        list.add(index, t);
    }

    public void remove(int index) {
        list.remove(index);
    }

    public ArrayList<Task> getTaskList() {
        return list;
    }

}
