/**
 * This class represents a specific command of deleting a task from Duke.
 */
package duke.commands;
import duke.exceptions.DukeException;
import duke.managers.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;
import duke.tasks.Task;
import java.io.IOException;

public class DeleteCommand extends Command {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private int taskToBeDeleted;

    public DeleteCommand(int taskNum) {
        this.taskToBeDeleted = taskNum;
    }

    /**
     * This method removes the target task from the list and prompts the user the number of remaining tasks saved.
     * @param tasks contains the data structure of Tasks stored in Duke
     * @param ui contains methods dealing with interaction with the user
     * @param storage contains methods to load and save information in the file
     * @exception DukeException is thrown when there is an error with the input
     * @exception IOException is thrown when there is an error saving the data in the hard disk
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
        int maxNum = tasks.totalNumTasks();
        if (taskToBeDeleted > maxNum) {
            throw new DukeException("Oops! This task number does not exist.");
        } else {
            Task deleted = tasks.delTask(taskToBeDeleted);
            this.ui.printLine("Noted. I've removed this task:");
            this.ui.printLine(deleted.toString());
            maxNum = tasks.totalNumTasks();
            this.ui.printLine("Now you have " + maxNum + " tasks in the list.");
            storage.save();
        }
    }

    public boolean isExit() {
        return false;
    }
}
