package dukepkg;

import dukepkg.exceptions.FormatException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<String> modifyingCommands = new ArrayList<>(){{add("done"); add("delete"); add("todo");add("deadline");add("event");}};
    public static ArrayList<Task> tasks;
    private static Ui ui;
    private static Storage storage;
    private static Parser parser;
    public TaskList(Storage storage, Ui ui, Parser parser) {
        tasks = new ArrayList<Task>();
        this.storage = storage;
        this.ui = ui;
        this.parser = parser;
    }

    public void loadTaskHistory() {
        try {
            tasks = storage.loadList();
        } catch (FileNotFoundException e) {
            ui.showLoadingError(e);
        }
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

}
