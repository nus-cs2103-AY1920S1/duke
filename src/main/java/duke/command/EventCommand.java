package duke.command;

import duke.task.Event;

import java.util.Date;

public class EventCommand extends AddCommand {

    public static final String COMMAND_WORD = "event";

    public EventCommand(String description, Date at) {
        super(new Event(description, at));
    }
}
