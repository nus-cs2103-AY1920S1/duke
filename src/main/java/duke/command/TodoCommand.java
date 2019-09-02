package duke.command;

import duke.exception.DukeException;
import duke.main.*;
import duke.task.*;

/**
 * Represents the command giving a todo task.
 */
public class TodoCommand implements Command {
    private String task;

    public TodoCommand(String task) {
        this.task = task;
    }

    /**
     * Adds a new ToDo object in the list of tasks and saves this new task list in the hard disk
     *
     * @param storage the Storage object to update the tasks in the file
     * @param ui the Ui object dealing with interactions with the user
     * @param tasks the TaskList object containing the existing list of tasks
     */
    public void execute(Storage storage, Ui ui, TaskList tasks) throws DukeException {
        Todo td = new Todo(task, false);
        tasks.addTask(td);
        ui.output(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list",
                td.toString(), tasks.getTasksSize()));
        storage.appendToFile(td);
    }

    public boolean isRunning() {
        return true;
    }
}
