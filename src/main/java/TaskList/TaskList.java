package TaskList;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> list = new ArrayList<>(100);

    public TaskList() {
    }

    public TaskList(ArrayList<Task> list) {

        this.list = list;
    }

    public void addList(Task t) {

        list.add(t);
    }

    public void delete(int taskNo) {

        list.remove(taskNo - 1);
    }
}

