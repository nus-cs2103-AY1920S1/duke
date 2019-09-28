package duke.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an Event task. An Event is a task that has
 * a 'description' and an 'at' date and time that task has to
 * be completed.
 */
public class Event extends Task {

    private char taskType = 'E';
    private SimpleDateFormat simpleDateParser = new SimpleDateFormat("dd/MM/yyyy, HHmm");
    private SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("dd MMMM yyyy, h.mma");
    protected Date at;

    public Event(String description, String at) {
        super(description);
        try {
            Date dateTime;
            if (at.contains("/")) {
                dateTime = simpleDateParser.parse(at);
            } else {
                dateTime = simpleDateFormatter.parse(at);
            }
            this.at = dateTime;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public Event(String status, String description, String at) {
        super(description);
        this.setStatus(status);
        try {
            Date dateTime;
            if (at.contains("/")) {
                dateTime = simpleDateParser.parse(at);
            } else {
                dateTime = simpleDateFormatter.parse(at);
            }
            this.at = dateTime;
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return String.format("[E][%s] " + super.toString() + " (at: " + getDate() + ")", super.getStatusIcon());
    }

    @Override
    public char getTaskType() {
        return taskType;
    }

    @Override
    public String getDate() {
        return simpleDateFormatter.format(at);
    }
}
