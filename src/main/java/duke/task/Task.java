package duke.task;

import duke.exception.DukeException;

public class Task {
    String description;
    boolean isDone;

    /**
     * Constructor to create a Task object.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the tick / cross icon for the status.
     *
     * @return The current status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Gets the current completion status of the task.
     *
     * @return True if done, false if the task is yet to be completed.
     */
    public boolean isCompleted() {
        return this.isDone;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Format the date and time.
     * @param year Year.
     * @param month Month.
     * @param date Date.
     * @param time Time.
     * @return The correct date and time format.
     */
    public String formatDateAndTime(int year, int month, int date, int time) throws DukeException {
        if (date > 31 || month > 12 || time > 2400) {
            throw new DukeException("OOPS!!! Incorrect date or time.\n");
        }
        StringBuilder sb = new StringBuilder();
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                           "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        sb.append(date);

        switch (date) {
        case 1:
            sb.append("st");
            break;
        case 2:
            sb.append("nd");
            break;
        case 3:
            sb.append("rd");
            break;
        default:
            sb.append("th");
            break;
        }
        sb.append(" of " + months[month - 1] + " " + year + ", ");

        if (time >= 1200) {
            if ((time / 100) == 12) {
                sb.append("12." + (time % 100) + "pm");
            } else {
                sb.append((time / 100 - 12) + "." + (time % 100) + "pm");
            }
        } else {
            if ((time / 100) == 0) {
                sb.append("12." + (time % 100) + "am");
            } else {
                sb.append((time / 100) + "." + (time % 100) + "am");
            }
        }
        return sb.toString();
    }

    /**
     * The proper representation of the task, with Task type, icon and description.
     *
     * @return The output representation of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}