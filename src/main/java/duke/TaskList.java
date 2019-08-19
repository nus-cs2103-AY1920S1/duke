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
    public void markDone(int oneIndex) {
        tasks.get(oneIndex-1).markDone();
    }
    public Task get(int oneIndex) {
        return tasks.get(oneIndex-1);
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
