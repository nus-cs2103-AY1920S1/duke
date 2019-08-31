/**
 * Deadlines are tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {

    protected DateTime dateTime;
    protected String timeInFile;

    /**
     * Assigns description, date and time to the deadline.
     *
     * @param description Activity that the user should complete.
     * @param dateTimeString String input to create a DateTime object for the deadline.
     */
    public Deadline(String description, String dateTimeString) {
        super(description);
        this.dateTime = dateTime.create(dateTimeString);
        this.timeInFile = dateTimeString;
    }

    public static Deadline parse(String[] fullCommand) {
        if (fullCommand.length == 1) {
            throw new DukeException("    ____________________________________________________________\n"
                    + "     OOPS!!! The description of a deadline cannot be empty :-(\n" + "    "
                    + "____________________________________________________________\n");
        }
        String[] detailsArray = fullCommand[1].split(" /by ", 2);
        if (detailsArray.length == 1) {
            throw new DukeException("    ____________________________________________________________\n"
                    + "     OOPS!!! Please specify a date and time for your deadline :-(\n" + "    "
                    + "____________________________________________________________\n");
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