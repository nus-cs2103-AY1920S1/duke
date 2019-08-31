package duke.command;

import duke.exception.DukeException;
import duke.main.*;
import duke.task.*;

public class DeadlineCommand implements Command {
    private String task;
    private String time;

    public DeadlineCommand(String task, String time) {
        this.task = task;
        this.time = time;
    }

    /**
     * Executes the deadline comment
     * @param storage
     * @param ui
     * @param tasks
     * @throws DukeException
     */
    public void execute(Storage storage, Ui ui, TaskList tasks) throws DukeException {
        Deadline dl = new Deadline(task, time, false);
        tasks.addTask(dl);
        ui.output(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list",
                dl.toString(), tasks.getTasksSize()));
        storage.appendToFile(dl);
    }

    public boolean isRunning() {
        return true;
    }
}
