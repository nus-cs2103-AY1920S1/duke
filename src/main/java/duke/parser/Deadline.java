package duke.parser;

import java.util.Date;
/**
 * Deadline task that is with tag D and tasktype of E with
 * description of the task and due time of the task.
 * @author Yang Shuting
 * @see Task
 * @see Deadine
 * @see Todo
 */
public class Deadline extends Task {
    public Deadline(String description, String by) {
        super(description.trim());
        super.tag = "[D]";
        super.information = "(by: " + by.trim() + ")";
        super.date = new Date(by.trim());
        super.taskType = TaskType.DEADLINE;

    }
}
