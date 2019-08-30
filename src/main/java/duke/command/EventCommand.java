package duke.command;

import duke.util.DateTime;
import duke.task.Event;

public class EventCommand extends NewTaskCommand {

    public EventCommand(String description, DateTime at) {
        super(new Event(description, at));
    }
}
