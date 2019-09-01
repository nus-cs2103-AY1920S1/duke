package duke.task;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.Ui;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * TaskList class, for in memory storage of tasks.
 */
public class TaskList implements Iterable<Task> {
    private Storage storage;
    private Ui ui;
    private ArrayList<Task> taskList;

    /**
     * Constructs a TaskList object.
     *
     * @param storage Disk storage for task list.
     * @param ui Ui for task list to write output.
     */
    public TaskList(Storage storage, Ui ui) {
        this.storage = storage;
        this.ui = ui;
        try {
            this.taskList = new ArrayList<Task>(this.storage.load());
        } catch (DukeException e) {
            this.ui.print(e.getMessage());
            this.taskList = new ArrayList<Task>();
        }
    }

    /**
     * Updates task list in disk storage.
     *
     * @throws DukeException if encountered IO exceptions.
     */
    private void updateDatabase() throws DukeException {
        this.storage.store(this.taskList);
    }

    /**
     * Adds the given task to task list.
     *
     * @param task Task to be added to task list.
     *
     * @throws DukeException if encountered IO exceptions while updating disk storage.
     */
    public void addNewTask(Task task) throws DukeException {
        this.taskList.add(task);
        this.ui.print("Got it! I've added this task:", task.toString());
        this.ui.print(taskList.size() == 1
            ? "Now you have 1 task in the list!"
            : "Now you have " + taskList.size() + " tasks in the list!");
        this.updateDatabase();
    }

    /**
     * Marks the given task as done in task list.
     *
     * @param index Index of task to be marked as done.
     *
     * @throws DukeException if encountered IO exceptions while updating disk storage.
     */
    public void doTask(int index) throws DukeException {
        Task task = taskList.get(index - 1);
        task.markAsDone();
        this.ui.print("Nice! I've marked this task as done:", task.toString());
        this.updateDatabase();
    }

    /**
     * Deletes the given task in task list.
     *
     * @param index Index of task to be deleted.
     *
     * @throws DukeException if encountered IO exceptions while updating disk storage.
     */
    public void deleteTask(int index) throws DukeException {
        Task task = taskList.remove(index - 1);
        this.ui.print("Noted! I've removed this task:", task.toString());
        this.ui.print(taskList.size() == 1
            ? "Now you have 1 task in the list!"
            : "Now you have " + taskList.size() + " tasks in the list!");
        this.updateDatabase();
    }

    /**
     * Prints the entire task list to ui.
     */
    public void printList() {
        this.ui.print("Here are the tasks in your list:");
        int counter = 1;
        for (Task task : this.taskList) {
            this.ui.print(counter++ + "." + task);
        }
    }

    /**
     * Prints an external task list to ui.
     *
     * @param taskList External task list to be printed.
     * @param ui Ui to be used for output.
     * @param message Message to be printed with the task items.
     */
    public static void printExternalList(ArrayList<Task> taskList, Ui ui, String message) {
        ui.print(message);
        int counter = 1;
        for (Task task : taskList) {
            ui.print(counter++ + "." + task);
        }
    }

    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }
}
