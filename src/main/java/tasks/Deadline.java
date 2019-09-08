package tasks;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    String by;
    LocalDate date1;

    /**
     * This is a constructor for Deadline.
     *
     * @param description description of task
     * @param by          due date of deadline
     */
    public Deadline(String description, String by) {
        super(description);
        assert by != null;
        this.by = by;
        super.symbol = "D";
    }

    /**
     * This method is used to return the task list.
     *
     * @return date
     */

    public LocalDate getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        date1 = LocalDate.parse(by, formatter);
        return date1;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getDate() + ")";
    }
}