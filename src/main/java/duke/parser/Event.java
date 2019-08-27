package duke.parser;

import java.util.Date;


public class Event extends Task {
    public Event(String description, String at) {
        super(description.trim());
        super.tag = "[E]";
        super.information = "(at: " + at.trim() + ")";
        Date date = new Date(at.trim());
        super.taskType = TaskType.EVENT;
    }
}
