package duke.task;

import duke.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class TaskWithDate extends Task {
    static final SimpleDateFormat TASK_WITH_DATE_FORMATTER = new SimpleDateFormat("dd 'of' MMMM yyyy, K:mma");
    static final SimpleDateFormat TASK_WITH_DATE_PARSER = new SimpleDateFormat("dd/MM/yyyy HHmm");
    protected Date date;

    public TaskWithDate(String description, boolean isDone, String date) throws ParseException {
        super(description, isDone);
        this.date = TASK_WITH_DATE_PARSER.parse(date);
    }

    public TaskWithDate(String description, String date) throws ParseException {
        super(description);
        this.date = TASK_WITH_DATE_PARSER.parse(date);
    }

    /**
     * The current format of input for a task with date is to add a /at or /on. This extracts the data from that format.
     * @param line Line of input
     * @param delimiter delimiter provided [" /at " or " /on "]
     * @return
     */
    public static String[] extractDataFromLine(String line, String delimiter) {
        return line.split(delimiter);
    }

    /**
     * Verify that the data is correct before creating a task with data.
     * @param data the data as extracted from extractDataFromLine
     * @param taskName Name of the task e.g. event or deadline
     * @return
     */
    public static boolean validateData(String[] data, String taskName) throws DukeException {
        if (data[0].length() <= 0) {
            throw new DukeException("The description of a " + taskName + " cannot be empty.");
        } else if (data.length <= 1 || data[1].length() <= 0) {
            throw new DukeException("The date of a " + taskName + " cannot be empty.");
        }
        return true;
    }

    public Date getDate() {
        return date;
    }

    public String getFormattedDateToString() {
        return TASK_WITH_DATE_FORMATTER.format(date);
    }

    public String getParseableDateToString() {
        return TASK_WITH_DATE_PARSER.format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
