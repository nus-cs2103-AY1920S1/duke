package dukepkg;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The Task list object used as a data structure to arrange all the tasks.
 */
public class TaskList {
    /**
     * The list of Tasks.
     */
    public static ArrayList<Task> tasks;
    private static Ui ui;
    private static Storage storage;

    /**
     * Instantiates a new Task list.
     *
     * @param storage the Storage object used to save and retrieve task data.
     * @param ui      the Ui object used to interact with the user.
     */
    public TaskList(Storage storage, Ui ui) {
        tasks = new ArrayList<>();
        TaskList.storage = storage;
        TaskList.ui = ui;
    }

    /**
     * Load task history.
     */
    public void loadTaskHistory() {
        try {
            tasks = Storage.loadList();
        } catch (FileNotFoundException e) {
            Ui.showLoadingError(e);
        }
    }

    /**
     * Add a task to the list.
     *
     * @param t the task to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    public void deleteTask(int index) { tasks.remove(index); }

}
