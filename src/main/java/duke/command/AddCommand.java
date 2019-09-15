package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;
import duke.task.Task;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     *
     * (Self-Review: Java passes non-primitive types as call-by-reference.
     * Before this, a Command object is instantiated. Next, execute method takes in
     * Duke's 3 objects and adds this 'held' task in this Command object to its Arraylist
     * Garage collection of this Command object is done later.)
     * @param tasks   duke.TaskList
     * @param ui      duke.ui.Ui
     * @param storage duke.Storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        storage.updateSaveFile(tasks);
        ui.show("Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}

