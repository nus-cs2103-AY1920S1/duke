package duke.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DeadlineTask extends Task {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d/M/yyyy HHmm");

    private Date dateTime;

    public DeadlineTask(String description, Date dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s(at: %s)", super.toString(), DATE_FORMAT.format(dateTime));
    }
}