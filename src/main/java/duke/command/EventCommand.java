package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.handler.Storage;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class EventCommand extends Command {
    protected String description;
    protected String at;

    public EventCommand(String description, String at) {
        this.description = description;
        this.at = at;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (description.equals("")) {
            throw new DukeException("The description of an event cannot be empty.");
        }
        Task eventTask = new Event(description, at);
        tasks.add(eventTask);
        response = "Got it. I've added this task:\n    " + eventTask + "\nNow you have " + tasks.size()
                + " task(s) in the " + "list.";
        storage.save(tasks);
    }
}
