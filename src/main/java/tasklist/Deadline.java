package tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Deadline type of task.
 */
public class Deadline extends Task {

    public Deadline(String name, boolean completionStatus, LocalDateTime date) {
        super(name,completionStatus, date);
    }

    @Override
    public String getOverallStatus() {
        return "[D]" + getCurrentStatus() + description.getValue() + "(by:" + date.format(OUTPUT_FORMAT) + ")";
    }

    @Override
    public String encodeForStorage() {
        int myInt = isDone.getValue() ? 1 : 0;
        return "deadline [" + myInt + "]" + description.getValue() + "/by"
                + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

}
