package duke.task;

import duke.exception.DukeException;
import duke.util.Storage;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * TaskList class, for in memory storage of tasks.
 */
public class TaskList implements Iterable<Task> {
    private Storage storage;
    private ArrayList<Task> taskList;

    /**
     * Constructs a TaskList object.
     *
     * @param storage Disk storage for task list.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        try {
            this.taskList = new ArrayList<Task>(this.storage.load());
        } catch (DukeException e) {
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
     * @return message to be printed.
     * @throws DukeException if encountered IO exceptions while updating disk storage.
     */
    public String[] addNewTask(Task task) throws DukeException {
        assert task != null : "Task cannot be null.";
        this.taskList.add(task);
        this.updateDatabase();
        return new String[] {
            "Got it! I've added this task:",
            task.toString(),
            taskList.size() == 1
                ? "Now you have 1 task in the list!"
                : "Now you have " + taskList.size() + " tasks in the list!"
        };
    }

    /**
     * Marks the given task as done in task list.
     *
     * @param index Index of task to be marked as done.
     * @return message to be printed.
     * @throws DukeException if encountered IO exceptions while updating disk storage.
     */
    public String[] doTask(int index) throws DukeException {
        Task task = taskList.get(index - 1);
        task.markAsDone();
        this.updateDatabase();
        return new String[] {
            "Nice! I've marked this task as done:",
            task.toString()
        };
    }

    /**
     * Deletes the given task in task list.
     *
     * @param index Index of task to be deleted.
     * @return message to be printed.
     * @throws DukeException if encountered IO exceptions while updating disk storage.
     */
    public String[] deleteTask(int index) throws DukeException {
        Task task = taskList.remove(index - 1);
        this.updateDatabase();
        return new String[] {
            "Noted! I've removed this task:",
            task.toString(),
            taskList.size() == 1
                ? "Now you have 1 task in the list!"
                : "Now you have " + taskList.size() + " tasks in the list!"
        };
    }

    /**
     * Tags the given task.
     *
     * @param index Index of task to be marked as done.
     * @param tag Tag to be added to the task.
     * @return message to be printed.
     * @throws DukeException if encountered IO exceptions while updating disk storage.
     */
    public String[] tagTask(int index, String tag) throws DukeException {
        Task task = taskList.get(index - 1);
        task.addTag(tag);
        this.updateDatabase();
        return new String[] {
            "Tag " + tag + " added to this task:",
            task.toString()
        };
    }

    /**
     * Prints the entire task list to ui.
     * @return list to be printed.
     */
    public String[] printList() {
        String[] output = new String[this.taskList.size() + 1];
        output[0] = "Here are the tasks in your list:";
        int counter = 1;
        for (Task task : this.taskList) {
            output[counter] = counter++ + "." + task;
        }
        return output;
    }

    /**
     * Prints an external task list to ui.
     *
     * @param taskList External task list to be printed.
     * @param message Message to be printed with the task items.
     * @return list to be printed.
     */
    public static String[] printExternalList(ArrayList<Task> taskList, String message) {
        String[] output = new String[taskList.size() + 1];
        output[0] = message;
        int counter = 1;
        for (Task task : taskList) {
            output[counter] = counter++ + "." + task;
        }
        return output;
    }

    @Override
    public Iterator<Task> iterator() {
        return taskList.iterator();
    }
}
