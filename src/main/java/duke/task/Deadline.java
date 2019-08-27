package duke.task;

import duke.task.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {

    private Date by;

    public Deadline(String description, String by) throws ParseException {
        super(description);
        this.by = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + new SimpleDateFormat("yyyy/MM/dd HH:mm").format(by) + ")";
    }
}