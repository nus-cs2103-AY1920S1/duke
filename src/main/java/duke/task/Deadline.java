package duke.task;

import duke.task.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.Date;

public class Deadline extends Task {

    private SimpleDateFormat deadline;
    protected String by;
    private Date date;

    public Deadline(String description, String by) throws DateTimeException {
        super(description);
        this.by = by;
        this.type = "D";

        this.deadline = new SimpleDateFormat("dd/MM/yyyy HHmm");
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getBy() {
        return this.by;
    }

    public String convertEventTime() throws ParseException, DateTimeException {

        try {
            date = deadline.parse(this.by);
            return deadline.format(date);
        } catch (ParseException exception) {
            System.out.println("OOPS!!! Please enter a valid deadline");
        }
        return "";
    }

    @Override
    public String toString() {
        try {
            return "[D]" + super.toString() + " (by: " + convertEventTime() + ")";
        } catch (ParseException exception) {
            return "Error";
        }
    }
}
