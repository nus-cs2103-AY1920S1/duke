package duke.task;

import java.util.Date;

/**
 * Event task that is with tag E and tasktype of Event with
 * description of the task and time which the event will held.
 * @author Yang Shuting
 * @see Task
 * @see Deadline
 * @see Todo
 */
public class Event extends Task {
    /**
     * constructor to create an event task.
     * @param description  description of the content
     * @param at the date and time
     */
    public Event(String description, String at) {
        super(description.trim());
        super.tag = "[E]";
        super.information = "(at: " + at.trim() + ")";
        super.date = new Date(at.trim());
        super.taskType = TaskType.EVENT;
    }
}
