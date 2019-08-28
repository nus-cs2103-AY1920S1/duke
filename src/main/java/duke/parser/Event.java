package duke.parser;

import java.util.Date;

/**
 * Event task that is with tag E and tasktype of Event with
 * description of the task and time which the event will held.
 * @author Yang Shuting
 * @see Task
 * @see Deadine
 * @see Todo
 */
public class Event extends Task {
    public Event(String description, String at) {
        super(description.trim());
        super.tag = "[E]";
        super.information = "(at: " + at.trim() + ")";
        Date date = new Date(at.trim());
        super.taskType = TaskType.EVENT;
    }
}
