package duke.commands;
import duke.exceptions.*;
import duke.managers.*;
import duke.tasks.*;
import java.io.IOException;

public class DoneCommand extends Command {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private int taskToBeDone;

    public DoneCommand(int taskNum) {
        this.taskToBeDone = taskNum;
    }

    /*
    This method marks the target task as done and prompts the user which task has been marked done.
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
