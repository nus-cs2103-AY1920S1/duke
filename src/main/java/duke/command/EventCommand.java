package duke.command;

import duke.DukeException.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.taskHandler.Storage;
import duke.ui.Ui;

import java.util.ArrayList;

public class EventCommand extends Command {
    protected String description;
    protected String at;

    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        Task eventTask = new Event(description, at);
        tasks.add(eventTask);
        ui.printAddedTask(eventTask, tasks);
    }
}
