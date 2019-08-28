import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    String endDate;
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
    Date date;

    /**
     * Constructor for Deadline event
     * @param taskName the name of the task
     * @param endDate the endDate of task if the format is dd/MM/yy HHmm will save as Date object
     */
    Deadline(String taskName, String endDate) {
        super(taskName);
        this.endDate = endDate;
        try {
            date = format.parse(endDate);
        } catch( Exception e ){
        }
    }

    /**
     * Get the end date
     * @return get the string representation of the endDate
     */
    public String getEndDate() {
        return this.endDate;
    }

    /**
     * get the full details of the task
     * @return a string representation of the task
     */
    public String getTaskDetails() {
        String doneSymbol;
        if (isDone()) {
            doneSymbol = "✓";
        } else {
            doneSymbol = "✗";
        }
        if (date == null) {
            return "[D]" + "[" + doneSymbol + "] " + getTaskName() + " (by: " + endDate + ")";
        } else {
            return "[D]" + "[" + doneSymbol + "] " + getTaskName() + " (by: " + date + ")";
        }
    }
}
