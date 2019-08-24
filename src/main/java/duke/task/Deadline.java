package duke.task;

import duke.task.TaskWithDate;

import java.text.ParseException;

public class Deadline extends TaskWithDate {

    public Deadline(String description, String date) throws ParseException {
        super(description, date);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + TASK_WITH_DATE_FORMATTER.format(date) + ")";
    }

    @Override
    public String getTaskName() {
        return "deadline";
    }
}