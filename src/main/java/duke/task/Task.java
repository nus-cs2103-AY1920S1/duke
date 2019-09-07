package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * abstract class Task.
 */

public abstract class Task {
    String description;
    boolean isDone;
    LocalDateTime pattern;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");


    public Task(String description) {
        this.description = perfectDescription(description);
        this.isDone = false;
    }


    public Task(String description, String done) {
        this.description = perfectDescription(description);
        if (done.trim().equals("1")) {
            this.isDone = true;
        }
    }

    /**
     * Gets the description of the task as a string.
     *
     * @return description of the task as a string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * perfectDescription.
     *
     * @param description The input is a string.
     * @return perfectDescription format
     */
    private String perfectDescription(String description) {
        String[] temp = description.split(" ");
        StringBuilder result = new StringBuilder();
        for (String str : temp) {
            if (!str.equals("")) {
                result.append(" ").append(str.trim());
            }
        }
        return result.toString().trim();
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the status icon.
     *
     * @return the tick icon if task is done, otherwise returns a cross symbol.
     */
    private String getStatusIcon() {
        return (isDone ? "V" : "X"); //return tick or X symbols
    }

    /**
     * get the String format to save in file.
     *
     * @return the format to save in file.
     */
    public abstract String getFormatToFile();

    /**
     * get the String time.
     *
     * @return the String format of time and date.
     */
    String getTime() {
        return pattern.format(formatter2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}