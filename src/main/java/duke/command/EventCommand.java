package duke.command;

import duke.DukeException;
import duke.task.Event;

public class EventCommand extends AddCommand {

    public EventCommand(String description, String at) throws DukeException {
        super(new Event(description, at));
    }

}