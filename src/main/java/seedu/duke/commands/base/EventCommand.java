package seedu.duke.commands.base;

import seedu.duke.exceptions.DukeException;
import seedu.duke.tasks.Event;
import seedu.duke.util.Storage;
import seedu.duke.util.TaskList;
import seedu.duke.util.UI;

import java.io.IOException;

/**
 * Command to add an Event task to the TaskList class.
 */
public class EventCommand extends Command {

    private Event eventToAdd;

    /**
     * Constructor that primes the event to be added to TaskList.
     *
     * @param description Description of event.
     * @param dateTime Date and time of event.
     * @throws DukeException Throws if dateTime is not of the valid format.
     */
    public EventCommand(String description, String dateTime) throws DukeException {
        eventToAdd = new Event(description, dateTime);
    }

    /**
     * Adds the event to the TaskList, inform the user that it has been done and writes the new change
     * to the text file.
     *
     * @param tasks TaskList of tasks to add the event to.
     * @param ui UI to display that event has been added.
     * @param storage Storage to write event to.
     * @return String that displays the event that was added.
     * @throws DukeException Throws if storage cannot find the file to write to.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        tasks.addToList(eventToAdd);
        try {
            storage.writeToFile(tasks);
        } catch (IOException ex) {
            throw new DukeException("OOPS!!! I cannot read your file! :(");
        }

        return ui.operateTask(eventToAdd, tasks, true);
    }
}
