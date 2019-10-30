package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a Deadline task. A Deadline is a task that has
 * a 'description' and a 'by' date and time that task has to
 * be completed.
 */
public class Deadline extends Task {

    private char taskType = 'D';
    private SimpleDateFormat simpleDateParser = new SimpleDateFormat("dd/MM/yyyy, HHmm");
    private SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("dd MMMM yyyy, h.mma");
    protected Date by;

    public Deadline(String description, String by) {
        super(description);
        try {
            Date dateTime;
            if (by.contains("/")) {
                dateTime = simpleDateParser.parse(by);
            } else {
                dateTime = simpleDateFormatter.parse(by);
            }
            this.by = dateTime;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public Deadline(String status, String description, String by) {
        super(description);
        this.setStatus(status);
        try {
            Date dateTime;
            if (by.contains("/")) {
                dateTime = simpleDateParser.parse(by);
            } else {
                dateTime = simpleDateFormatter.parse(by);
            }
            this.by = dateTime;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return String.format("[D][%s] " + super.toString() + " (by: " + getDate() + ")", super.getStatusIcon());
    }

    @Override
    public char getTaskType() {
        return taskType;
    }

    @Override
    public String getDate() {
        return simpleDateFormatter.format(by);
    }
}
