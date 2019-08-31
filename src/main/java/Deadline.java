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
     * Parses the command given to Duke and creates a deadline task if possible.
     *
     * @param fullCommand Full command split by the word "deadline".
     * @return Deadline object created.
     * @throws DukeException If the deadline has no description, or no date/time.
     */
    public static Deadline parse(String[] fullCommand) throws DukeException {
        if (fullCommand.length == 1) {
            throw new DukeException("     OOPS!!! The description of a deadline cannot be empty :-(");
        }
        String[] detailsArray = fullCommand[1].split(" /by ", 2);
        if (detailsArray.length == 1) {
            throw new DukeException("     OOPS!!! Please specify a date and time for your deadline :-(");
        }
        return new Deadline(detailsArray[0], detailsArray[1]);
    }

    /**
     * Formats the deadline to be stored in the hard disk.
     *
     * @return Deadline details in the format D | 1 or 0 | description | time.
     */
    @Override
    public String toSave() {
        return "D | " + super.getBinaryStatus() + " | " + super.description + " | " + timeInFile;
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