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

    public ArrayList<Task> returnAllMatchingTasks(String keyWord) {
        ArrayList<Task> lst = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            if (t.getDescription().toLowerCase().contains(keyWord.toLowerCase())) {
                lst.add(t);
            }
        }
        return lst;
    }

}
