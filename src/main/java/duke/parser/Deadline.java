package duke.parser;

import java.util.Date;

public class Deadline extends Task {
    public Deadline(String description, String by) {
        super(description.trim());
        super.tag = "[D]";
        super.information = "(by: " + by.trim() + ")";
        super.date = new Date(by.trim());
        super.taskType = TaskType.DEADLINE;

    }
}
