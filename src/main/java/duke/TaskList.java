package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> tasks = new ArrayList<>();
    public TaskList() {

    }
    public void add(Task t) {
        tasks.add(t);
    }
    @Override
    public String toString() {
        String s = "";
        for(int i = 0; i < tasks.size(); i++) {
            s = s + String.format("%d. %s\n", (i+1), tasks.get(i).toString());
        }
        return s;
    }
}
