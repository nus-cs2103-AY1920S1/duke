package duke.task;

import duke.util.DukeDate;

public class Deadline extends Task {
    /**
     * The deadline this task must be completed by.
     * It is a String to allow the user to custom dates such as 'the day after tomorrow'
     */
    protected DukeDate date;

    public Deadline(String desc, String dateString) {
        super(desc);
        this.date = DukeDate.parse(dateString);
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatusIcon(), description, date.toString());
    }
}
