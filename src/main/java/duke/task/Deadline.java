package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private char shortForm = 'D';
    protected LocalDateTime submissionTime;

    //Used when user entered command
    public Deadline(String name, LocalDateTime submissionTime) {
        super(name);
        this.submissionTime = submissionTime;
    }

    //Used when program loads data from text
    public Deadline(String name, LocalDateTime submissionTime, boolean isDone) {
        super(name, isDone);
        this.submissionTime = submissionTime;
    }

    public LocalDateTime getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(LocalDateTime submissionTime) {
        this.submissionTime = submissionTime;
    }

    /**
     * Formats the datetime.
     * @return formatted datetime
     */
    public String getFormattedDateTime() {
        return submissionTime.format(DateTimeFormatter.ofPattern("dd MMMM yyyy, hh:mm a"));
    }

    @Override
    public char getShortForm() {
        return shortForm;
    }

    @Override
    public String toString() {
        return "[" + getShortForm() + "]" + super.toString() + " (by: " + getFormattedDateTime() + ")";
    }
}
