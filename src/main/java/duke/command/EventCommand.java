package duke.command;

import duke.main.*;
import duke.task.*;

public class EventCommand implements Command {
    private String task;
    private String time;

    public EventCommand(String task, String time) {
        this.task = task;
        this.time = time;
    }

    public void execute(Storage storage, Ui ui, TaskList tasks) {
        Event ev = new Event(task, time);
        tasks.addTask(ev);
        ui.output(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list",
                ev.toString(), tasks.getTasksSize()));
    }

    public boolean isRunning() {
        return true;
    }
}
