package duke.command;

import duke.util.DateTime;
import duke.task.Event;

/**
 * Class representing a command issued by the user to create an
 * Event object. Inherits from the NewTaskCommand abstract class.
 * @see NewTaskCommand
 * @see Event
 */
public class EventCommand extends NewTaskCommand {

    public static final String KEYWORD = "event";


    /**
     * Returns an EventCommand object that can be executed to
     * create an Event object and add it to the current TaskList.
     *
     * @param description description of Event object to be created
     * @param at DateTime object representing date and time to be associated with the Event object
     */
    public EventCommand(String description, DateTime at) {
        super(new Event(description, at));
    }
}
