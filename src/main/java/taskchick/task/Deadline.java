package taskchick.task;

import taskchick.datetime.DateTime;
import taskchick.exception.TaskChickException;

/**
 * Deadlines are tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {

    protected DateTime dateTime;
    protected String timeInFile;

    /**
     * Creates a deadline task with the specified description and date/time.
     *
     * @param description Deadline to complete.
     * @param dateTimeString Date/time of the deadline.
     */
    public Deadline(String description, String dateTimeString) {
        super(description);
        this.dateTime = dateTime.create(dateTimeString);
        this.timeInFile = dateTimeString;
    }

    /**
     * Parses the command given to Task Chick and creates a deadline task if possible.
     *
     * @param fullCommand Full command split by the word "deadline".
     * @return Deadline object created.
     * @throws TaskChickException If the deadline has no description, or no date/time.
     */
    public static Deadline process(String[] fullCommand) throws TaskChickException {
        if (fullCommand.length == 1) {
            throw new TaskChickException("OOPS!!! The description of a deadline cannot be empty :-(");
        }
        String[] detailsArray = fullCommand[1].split(" /by ", 2);
        if (detailsArray.length == 1) {
            throw new TaskChickException("OOPS!!! Please specify a date and time for your deadline :-(");
        }
        try {
            return new Deadline(detailsArray[0], detailsArray[1]);
        } catch (TaskChickException e) {
            throw new TaskChickException(e.getMessage());
        }
    }

    /**
     * Retrieves the date of this deadline.
     *
     * @return Date in the array {dd, mm, yyyy}.
     */
    @Override
    public int[] getDate() {
        return dateTime.getDate();
    }

    /**
     * Formats the event to be stored in the hard disk.
     *
     * @return Deadline details in the format D | v or x | description | time.
     */
    @Override
    public String toSave() {
        return "D | " + super.getStatusIcon() + " | " + super.description + " | " + timeInFile;
    }

    /**
     * Formats the deadline to be displayed to the user.
     *
     * @return Deadline details in the format [D][v or x] description (at: date and time).
     */
    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + dateTime + ")";
    }
}