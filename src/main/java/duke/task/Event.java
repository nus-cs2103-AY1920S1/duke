package duke.task;

import java.text.ParseException;

public class Event extends TaskWithDate {

    public Event(String description, String date) throws ParseException {
        super(description, date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + TASK_WITH_DATE_FORMATTER.format(date) + ")";
    }

    @Override
    public String getTaskName() {
        return "event";
    }
}