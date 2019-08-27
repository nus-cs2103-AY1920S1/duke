package dukepkg;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TaskList {
    public static ArrayList<Task> tasks;
    private static Ui ui;
    private static Storage storage;

    public TaskList(Storage storage, Ui ui) {
        tasks = new ArrayList<>();
        TaskList.storage = storage;
        TaskList.ui = ui;
    }

    public void loadTaskHistory() {
        try {
            tasks = Storage.loadList();
        } catch (FileNotFoundException e) {
            Ui.showLoadingError(e);
        }
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

}
