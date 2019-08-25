package duke.task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    private String deadlineBy;
    private Date deadlineDate;

    public Deadline(String description, String deadlineBy) {
        super(description);
        this.deadlineBy = deadlineBy;
    }

    public Deadline(String description, Date deadlineDate) {
        super(description);
        this.deadlineDate = deadlineDate;
    }

    @Override
    public String getOutputFormat() {
        return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, deadlineBy);
    }

    @Override
    public String toString() {
        if (deadlineDate != null) {
            DateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            return String.format("[D]%s (by: %s)", super.toString(), dtf.format(deadlineDate));
        } else {
            return String.format("[D]%s (by: %s)", super.toString(), deadlineBy);
        }
    }
}
