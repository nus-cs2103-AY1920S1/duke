import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * An object representing the list of tasks,
 * that reads, writes, and updates task information to the hard disk.
 */
public class TaskList {
    private ArrayList<Task> listItems;
    private PrintStream ps;
    private Storage storage;
    private Ui ui;

    protected TaskList(Storage storage) {
        this.ui = new Ui();
        this.ps = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        try {
            this.storage = storage;
            this.listItems = storage.load();
        } catch (DukeException e) {
            ui.printException(e);
        }
    }

    protected ArrayList<Task> getListItems() {
        return listItems;
    }

    /**
     * Adds a task to the list based on the given command.
     * @param command The command given by the user to be processed.
     * @throws DukeException Exception thrown during the creation of
     *     the Task object if the command is invalid.
     */
    protected void addTask(String command) throws DukeException {
        Task newTask = Task.create(command);
        storage.addNew(newTask.toFileString());
        listItems.add(newTask);
        ui.taskListAdd(newTask.toString(), listItems.size());
    }

    /**
     * Mark a task in the list as done based on its ID.
     * @param id the ID of the task that is done.
     * @throws DukeException Exception thrown if the ID input is invalid.
     */
    protected void markAsDone(int id) throws DukeException {
        if (id > listItems.size() || id <= 0) {
            throw new DukeException("The ID that you have entered is not a valid task ID.");
        }
        Task task = listItems.get(id - 1);
        task.setDone();
        storage.updateSaveFile(listItems);
        ui.taskListDone(task.toString());
    }

    /**
     * Delete a task in the list based on its ID.
     * @param id the ID of the task that is to be deleted.
     * @throws DukeException Exception thrown if the ID input is invalid.
     */
    protected void delete(int id) throws DukeException {
        if (id > listItems.size() || id <= 0) {
            throw new DukeException("The ID that you have entered is not a valid task ID.");
        }
        Task task = listItems.remove(id - 1);
        storage.updateSaveFile(listItems);
        ui.taskListDelete(task.toString(), listItems.size());
    }

    /**
     * Searches the list for tasks that match the user specified keyword.
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
}
