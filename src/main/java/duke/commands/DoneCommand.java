/**
 * This class represents a specific command of marking a task in Duke as done.
 */
package duke.commands;
import duke.exceptions.DukeException;
import duke.managers.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;
import duke.tasks.Task;
import java.io.IOException;

public class DoneCommand extends Command {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private int taskToBeDone;

    public DoneCommand(int taskNum) {
        this.taskToBeDone = taskNum;
    }

    /**
     * This method marks the target task as done and prompts the user which task has been marked done.
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
        if (taskToBeDone > maxNum) {
            throw new DukeException("Oops! This task number does not exist.");
        } else {
            Task targetTask = tasks.getTask(taskToBeDone);
            targetTask.markAsDone();
            this.ui.printLine("Nice! I've marked this task as done:");
            this.ui.printLine(targetTask.toString());
            storage.save();
        }
    }

    public boolean isExit() {
        return false;
    }
}
