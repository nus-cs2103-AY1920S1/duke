import java.util.ArrayList;

/**
 * An object representing the list of tasks,
 * that reads, writes, and updates task information to the hard disk.
 */
public class TaskList {
    private ArrayList<Task> listItems;
    private Storage storage;
    private Ui ui;

    TaskList(Storage storage, Ui ui) throws IceBearException {
        this.ui = ui;
        this.storage = storage;
        this.listItems = storage.load();
    }

    protected ArrayList<Task> getListItems() {
        return listItems;
    }

    /**
     * Adds a task to the list based on the given command.
     *
     * @param command The command given by the user to be processed.
     * @throws IceBearException Exception thrown during the creation of
     *     the Task object if the command is invalid.
     */
    protected void addTask(String command) throws IceBearException {
        Task newTask = Task.create(command);
        storage.addNew(newTask.toFileString());
        listItems.add(newTask);
        ui.taskListAdd(newTask.toString(), listItems.size());
    }

    /**
     * Mark a task in the list as done based on its ID.
     *
     * @param id the ID of the task that is done.
     * @throws IceBearException Exception thrown if the ID input is invalid.
     */
    protected void markAsDone(int id) throws IceBearException {
        if (id > listItems.size() || id <= 0) {
            throw new IceBearException("The ID that you have entered is not a valid task ID.");
        }
        Task task = listItems.get(id - 1);
        task.setDone();
        storage.updateSaveFile(listItems);
        ui.taskListDone(task.toString());
    }

    /**
     * Delete a task in the list based on its ID.
     *
     * @param id the ID of the task that is to be deleted.
     * @throws IceBearException Exception thrown if the ID input is invalid.
     */
    protected void delete(int id) throws IceBearException {
        if (id > listItems.size() || id <= 0) {
            throw new IceBearException("The ID that you have entered is not a valid task ID.");
        }
        Task task = listItems.remove(id - 1);
        storage.updateSaveFile(listItems);
        ui.taskListDelete(task.toString(), listItems.size());
    }

    /**
     * Searches the list for tasks that match the user specified keyword.
     *
     * @param keyword The keyword specified by the user.
     */
    protected void find(String keyword) {
        ArrayList<Task> matchList = new ArrayList<>();
        for (Task task : listItems) {
            if (task.details.contains(keyword)) {
                matchList.add(task);
            }
        }
        ui.printMatchList(matchList);
    }

    /**
     * Undoes the last action made by the user.
     *
     * @throws IceBearException Exception thrown when there is nothing to undo.
     */
    protected void undo() throws IceBearException {
        this.listItems = storage.undo();
        ui.taskListUndo();
    }
}
