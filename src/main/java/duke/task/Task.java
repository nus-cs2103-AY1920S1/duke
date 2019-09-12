package duke.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Represents a Task object.
 * Super class of ToDos, Events, and Deadlines.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Prints any Calendar object in a specific user-friendly date and time format.
     * Used by Event and Deadline classes.
     * @param time Calendar object that needs to be printed
     * @return String representing the date and time of Calendar object in specific format
     */
    public static String printDateAndTime(Calendar time) {
        String jargon = "";
        switch (time.get(Calendar.DAY_OF_MONTH) % 10) {
        case 1 : jargon = "st ";
            break;
        case 2 : jargon = "nd ";
            break;
        case 3 : jargon = "rd ";
            break;
        default : jargon = "th ";
        }
        if (time.get(Calendar.DAY_OF_MONTH) / 10 == 1) {
            jargon = "th ";
        }
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMM, YYYY. h:mma");
        String dateWithoutDay = dateFormatter.format(time.getTime()).replace("AM","am").replace("PM","pm");
        return time.get(Calendar.DAY_OF_MONTH) + jargon
                + dateWithoutDay;
    }

    /**
     * Creates a task object with description as input.
     * @param des description of task.
     */
    public Task(String des) {
        description = des;
        isDone = false;
    }

    /**
     * Returns an icon according to whether the task is done or not.
     * @return '+' if task is done and '-' if task is not done.
     */
    public String getStatusIcon() {
        return isDone ? "+" : "-";
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns description of Task.
     * @return description of Task.
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
