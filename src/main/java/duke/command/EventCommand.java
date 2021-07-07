package duke.command;

import duke.DukeException;
import duke.task.Event;

/**
 * Handles the command that requires adding an event to the list of tasks.
 */
public class EventCommand extends AddCommand {

    public EventCommand(String description, String at) throws DukeException {
        super(new Event(description, at));
    }

    public EventCommand(String description, String by, int freq) throws DukeException {
        super(new Event(description, by, freq));
    }

}