package duke;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Deadline extends Task {
    private Date date;
    private String description;

    public Deadline(String description) {
        super(description);
        this.type = "D";
    }

    public void parseTime(String time) throws ParseException {
        this.date = new SimpleDateFormat("dd/MM/yyyy hhmm").parse(time);
    }
    @Override
    public String toString() {
        return "[" + this.type + "][" + this.getStatusIcon() + "] " + this.description + this.date;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            Deadline deadline = (Deadline) o;
            return deadline.toString().equals(this.toString());
        }
        return false;
    }
}
