package seedu.duke.commands;

import seedu.duke.exceptions.DukeException;
import seedu.duke.tasks.Event;
import seedu.duke.util.Storage;
import seedu.duke.util.TaskList;
import seedu.duke.util.UI;

import java.io.IOException;

public class EventCommand extends Command {

    private Event eventToAdd;

    public EventCommand(String description, String dateTime) throws DukeException {
        eventToAdd = new Event(description, dateTime);
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        tasks.addToList(eventToAdd);
        ui.operateTask(eventToAdd, tasks, true);
        try {
            storage.writeToFile(tasks);
        } catch (IOException ex) {
            throw new DukeException("☹ OOPS!!! I cannot read your file! :(");
        }
    }
}
