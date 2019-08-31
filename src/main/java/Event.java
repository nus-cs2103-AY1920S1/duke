/**
 * Events are tasks that start at a specific time and ends at a specific time.
 */
public class Event extends Task {

    protected DateTime dateTime;
    protected String timeInFile;

    /**
     * Assigns description, date and time to the event.
     *
     * @param description Activity that the user should attend.
     * @param dateTimeString String input to create a DateTime object for the event.
     */
    public Event(String description, String dateTimeString) {
        super(description);
        this.dateTime = dateTime.create(dateTimeString);
        this.timeInFile = dateTimeString;
    }

    public static Event parse(String[] fullCommand) {
        if (fullCommand.length == 1) {
            throw new DukeException("    ____________________________________________________________\n"
                    + "     OOPS!!! The description of an event cannot be empty :-(\n" + "    "
                    + "____________________________________________________________\n");
        }
        String[] detailsArray = fullCommand[1].split(" /at ", 2);
        return new Event(detailsArray[0], detailsArray[1]);
    }

    /**
     * Formats the event to be stored in the hard disk.
     *
     * @return Event details in the format E | 1 or 0 | description | time.
     */
    @Override
    public String toSave() {
        return "E | " + super.getBinaryStatus() + " | " + super.description + " | " + timeInFile;
    }

    /**
     * Formats the event to be displayed to the user.
     *
     * @return Event details in the format [E][v or x] description (at: date and time).
     */
    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + dateTime + ")";
    }
}