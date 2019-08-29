package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

public class Deadline extends Task {
    private Date by;

    public Deadline(String description, String by) throws ParseException {
        super(description);
        this.by = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(by);
    }

    @Override
    public String toString() {
        return "D -- " + super.toString() + " -- " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(by);
    }
}
