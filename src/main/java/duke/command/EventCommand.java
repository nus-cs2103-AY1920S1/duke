package duke.command;

import duke.exception.DukeException;
import duke.main.*;
import duke.task.*;

/**
 * Represents the command giving a event task.
 */
public class EventCommand implements Command {
    private String task;
    private String time;

    public EventCommand(String task, String time) {
        this.task = task;
        this.time = time;
    }

    /**
     * Adds a new Event object in the list of tasks and saves this new task list in the hard disk
     *
     * @param storage the Storage object to update the tasks in the file
     * @param ui the Ui object dealing with interactions with the user
     * @param tasks the TaskList object containing the existing list of tasks
     */
    public void execute(Storage storage, Ui ui, TaskList tasks) throws DukeException {
        Event ev = new Event(task, time, false);
        tasks.addTask(ev);
        ui.output(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list",
                ev.toString(), tasks.getTasksSize()));
        storage.appendToFile(ev);
    }

    public boolean isRunning() {
        return true;
    }
}
