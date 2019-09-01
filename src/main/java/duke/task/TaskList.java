package duke.task;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.Ui;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskList implements Iterable<Task> {
    private Storage storage;
    private Ui ui;
    private ArrayList<Task> taskList;

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

    private void updateDatabase() throws DukeException {
        this.storage.store(this.taskList);
    }

    public void addNewTask(Task task) throws DukeException {
        this.taskList.add(task);
        this.ui.print("Got it! I've added this task:", task.toString());
        this.ui.print(taskList.size() == 1
            ? "Now you have 1 task in the list!"
            : "Now you have " + taskList.size() + " tasks in the list!");
        this.updateDatabase();
    }

    public void doTask(int index) throws DukeException {
        Task task = taskList.get(index - 1);
        task.markAsDone();
        this.ui.print("Nice! I've marked this task as done:", task.toString());
        this.updateDatabase();
    }

    public void deleteTask(int index) throws DukeException {
        Task task = taskList.remove(index - 1);
        this.ui.print("Noted! I've removed this task:", task.toString());
        this.ui.print(taskList.size() == 1
            ? "Now you have 1 task in the list!"
            : "Now you have " + taskList.size() + " tasks in the list!");
        this.updateDatabase();
    }

    public void printList() {
        this.ui.print("Here are the tasks in your list:");
        int counter = 1;
        for (Task task : this.taskList) {
            this.ui.print(counter++ + "." + task);
        }
    }

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
