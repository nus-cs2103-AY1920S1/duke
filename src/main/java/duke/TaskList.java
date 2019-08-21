package duke;

import java.util.ArrayList;

class TaskList {
    private ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    boolean addTask(final Task task) {
        return tasks.add(task);
    }

    Task getTask(final int index) {
        return tasks.get(index);
    }

    int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < tasks.size(); ++i) {
            ret.append(i + 1)
                .append(".")
                .append(tasks.get(i).toString())
                .append("\n");
        }
        return ret.toString();
    }
}
