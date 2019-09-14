package tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class Task implements Comparable<Task> {
    private String description;
    private boolean isDone;
    String symbol;
    private boolean hasNotes = false;
    ArrayList<String> noteList;

    /**
     * This is a constructor for Task.
     * @param description description of task
     */

    public Task(String description) {
        noteList = new ArrayList<>();
        assert description != null;
        this.description = description;
        this.isDone = false;
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * This method is used return status icon of a task.
     *
     * @return String status icon
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public abstract LocalDateTime getDateTime();

    /**
     * This method is used return description of a task.
     *
     * @return String description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * This method is used to return extra information such as
     * the time of a task (if any).
     *
     * @return String extra information
     */
    public abstract String getExtraInfo();

    /**
     * This method is used to mark a task as done.
     */

    public void markAsDone() {
        assert !isDone;
        isDone = true;
    }

    /**
     * Adds notes to a task.
     * @param notes notes to add to task
     */
    public void addNotes(String notes) {
        noteList.add(notes);
        hasNotes = true;
    }


    public abstract void postpone(int daysToPostpone, int hoursToPostpone, int minutesToPostpone);

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Gets the notes written by user.
     * @return notes in string format
     */
    public String getNotes() {
        if (!hasNotes) {
            return "";
        }
        String output = "";
        for (String note: noteList) {
            output += (" #" + note);
        }
        return output;
    }

    public int compareTo(Task other) {
        if (getSymbol() == "T") {
            if (other.symbol == "T") {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (other.getSymbol() == "T") {
                return -1;
            } else {
                return getDateTime().compareTo(other.getDateTime());
            }
        }
    }
}

