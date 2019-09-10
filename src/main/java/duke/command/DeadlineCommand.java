package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.main.TaskList;
import duke.task.Deadline;

/**
 * Represents the command giving a deadline task.
 */
public class DeadlineCommand implements Command {
    private String task;
    private String time;

    public DeadlineCommand(String task, String time) {
        this.task = task;
        this.time = time;
    }

    /**
     * Adds a new Deadline object in the list of tasks and saves this new task list in the hard disk
     *
     * @param storage the Storage object to update the tasks in the file
     * @param ui the Ui object dealing with interactions with the user
     * @param tasks the TaskList object containing the existing list of tasks
     */
    public String execute(Storage storage, Ui ui, TaskList tasks) throws DukeException {
        Deadline dl = new Deadline(task, time, false);
        tasks.addTask(dl);
        storage.appendToFile(dl);
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list",
                dl.toString(), tasks.getTasksSize());
    }

    public boolean isRunning() {
        return true;
    }
}
