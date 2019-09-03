package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.Ui;
import duke.main.TaskList;
import duke.task.Event;

public class EventCommand implements Command {
    private String task;
    private String time;

    public EventCommand(String task, String time) {
        this.task = task;
        this.time = time;
    }

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
