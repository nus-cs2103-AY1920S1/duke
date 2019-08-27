package duke.command;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> store;

    public TaskList(ArrayList<Task> store) {
        TaskList.store = store;
    }

    public TaskList() {
        store = new ArrayList<>();
    }

    static ArrayList<Task> getList() {
        return store;
    }
}
