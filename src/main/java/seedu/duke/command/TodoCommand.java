package duke.command;

import duke.exception.DukeException;
import duke.main.*;
import duke.task.*;

public class TodoCommand implements Command {
    private String task;

    public TodoCommand(String task) {
        this.task = task;
    }

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
