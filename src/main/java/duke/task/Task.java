package duke.task;

import duke.exception.DukeException;

public abstract class Task {
    String description;
    boolean isDone;

    /**
     * Default constructor.
     */
    public Task() {
    }

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
     *
     * @param year Year.
     * @param month Month.
     * @param date Date.
     * @param time Time.
     * @return The date and time in the correct format.
     */
    public String formatDateAndTime(int year, int month, int date, int time) throws DukeException {
        if (date > 31 || month > 12 || time > 2400) {
            throw new DukeException("OOPS!!! Incorrect date or time.\n");
        }
        String formattedDate = formatDate(year, month, date);
        String formattedTime = formatTime(time);
        return formattedDate + formattedTime;
    }

    /**
     * Helper method to format the time.
     *
     * @param time Time.
     * @return The time in the correct format.
     */
    private String formatTime(int time) {
        StringBuilder sb = new StringBuilder();
        if (time >= 1200) {
            if ((time / 100) == 12) {
                sb.append("12." + String.format("%02d", (time % 100)) + "pm");
            } else {
                sb.append((time / 100 - 12) + "." + String.format("%02d", (time % 100)) + "pm");
            }
        } else {
            if ((time / 100) == 0) {
                sb.append("12." + String.format("%02d", (time % 100)) + "am");
            } else {
                sb.append((time / 100) + "." + String.format("%02d", (time % 100)) + "am");
            }
        }
        return sb.toString();
    }

    /**
     * Helper method to format the date.
     *
     * @param year Year.
     * @param month Month.
     * @param date Date.
     * @return The date in the correct format.
     */
    private String formatDate(int year, int month, int date) {
        StringBuilder sb = new StringBuilder();
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun",
                           "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        sb.append(date);

        switch (date) {
        case 1: case 21: case 31:
            sb.append("st");
            break;
        case 2: case 22:
            sb.append("nd");
            break;
        case 3: case 23:
            sb.append("rd");
            break;
        default:
            sb.append("th");
            break;
        }
        sb.append(" of " + months[month - 1] + " " + year + ", ");
        return sb.toString();
    }

    /**
     * Helper method to separate date and time from the string provided.
     *
     * @param dateTime Date and time in input format.
     * @return int Array with date, month, year and time.
     */
    public int[] getDateTimeArray(String dateTime) throws DukeException {
        try {
            int[] dateTimeArray = new int[4];
            dateTimeArray[0] = Integer.parseInt(dateTime.substring(0, 2));
            dateTimeArray[1] = Integer.parseInt(dateTime.substring(3, 5));
            dateTimeArray[2] = Integer.parseInt(dateTime.substring(6, 10));
            String[] temp = dateTime.split(" ");
            dateTimeArray[3] = Integer.parseInt(temp[1]);
            return dateTimeArray;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Wrong input format");
        }
    }

    /**
     * Abstract method to get string with type and description for duplication check.
     *
     * @return String to check for duplicates.
     */
    public abstract String getDuplicateCheckString();

    /**
     * The proper representation of the task, with Task type, icon and description.
     *
     * @return The output representation of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}