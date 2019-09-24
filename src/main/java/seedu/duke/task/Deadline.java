package seedu.duke.task;

import seedu.duke.core.DukeException;
import seedu.duke.statistic.Statistic;
import java.time.LocalDateTime;

/**
 * Deadline class is a subclass of Task class.
 * Additional attribute is the String dateTime, which holds the dateTime of the Deadline task.
 */
public class Deadline extends Task {


    protected String dueDateTime;

    /**
     * Returns a Deadline object after initializing with 2 Strings, description and dateTime.
     *
     * @param description Description String of the task.
     * @param dueDateTime dateTime String of the task.
     */
    public Deadline(String description, String dueDateTime) throws DukeException {
        // Sets isDone to the default value, false.
        // Possible String for dateTime = 2/12/2019 1800.
        // Sample fullCommand = "deadline cs /by 21/12/2019 0800".
        super(description);
        this.dueDateTime = dueDateTime;

        if (!dueDateTime.contains("of")) {
            // Checks if the String has already been formatted.
            // If "of" is present, this indicated that dateTime has already been formatted.
            // Main purpose is for creating Deadline objects when reading from saved data.
            this.dueDateTime = parseBy(dueDateTime);
        }
    }

    /**
     * Returns a Deadline object after initializing with description, dateTime, isDone,
     * createdDate and lastModified date of the task. Meant to be used when constructing object from saved data.
     *
     * @param description Description String of the task.
     * @param dueDateTime dateTime String of the task.
     * @param isDone isDone Boolean status of the task.
     * @param createDateTime LocalDateTime object.
     * @param lastModifiedDateTime LocalDateTime object.
     */
    public Deadline(String description, String dueDateTime, Boolean isDone, LocalDateTime createDateTime,
                    LocalDateTime lastModifiedDateTime) throws DukeException  {
        super(description, isDone, createDateTime, lastModifiedDateTime);
        this.dueDateTime = dueDateTime;

        if (!dueDateTime.contains("of")) {
            // Checks if the String has already been formatted.
            // If "of" is present, this indicated that dateTime has already been formatted.
            // Main purpose is for creating Deadline objects when reading from saved data.
            this.dueDateTime = parseBy(dueDateTime);

        }
    }

    /**
     * Returns a parsed String of the Deadline object.
     * Eg. description = "Assignment", dateTime = "0th of December 2012, 6.42pm", isDone = false.
     * Parsed String = "[D][✘] Assignment  (by: 20th of December 2012, 6.42pm)".
     *
     * @return Parsed String of the Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDateTime + ")";
    }

    /**
     * Getter function for dueDateTime String.
     *
     * @return dueDateTime String.
     */
    public String getDueDateTime() {
        return this.dueDateTime;
    }

    /**
     * Returns a parsed String, meant for saving, of the Task object.
     *Eg. description = "Assignment", dateTime = "0th of December 2012, 6.42pm", isDone = true.
     * Parsed saved String = "D | 1 | Assignment  | 20th of December 2012, 6.42pm".
     *
     * @return Parsed string for saving.
     */
    @Override
    public String toSaveString() {

        return ("D" + super.toSaveString() + " | " + this.getDueDateTime() + " | "
                + this.getCreateDateTime().toString() + " | " + this.getLastModifiedDateTime().toString());
    }

    /**
     * Returns a parsed dateTime String.
     * String "by" must be of the format DD/MM/YYYY HHHH Eg. "31/08/2019 1215".
     * Will be converted to "31st of August 2019, 12.15pm".
     *
     * @param by Unparsed dateTime String.
     * @return Parsed dateTime String.
     */
    public String parseBy(String by) throws DukeException {

        taskType = PossibleTaskTypes.DEADLINE;

        String[] words = dueDateTime.split("/");
        String[] years = dueDateTime.split(" ");

        int day = Integer.parseInt(words[0]);

        assert day > 0 : "Day should be strictly positive";
        assert day < 32 : "Day should be less than 32";

        String hour = years[1];


        String dayString;
        if ((day == 1) || (day == 21) || (day == 31)) {
            dayString = "st";
        } else if ((day == 2) || (day == 22)) {
            dayString = "nd";
        } else if ((day == 3) || (day == 23)) {
            dayString = "rd";
        } else {
            dayString = "th";
        }

        if (day > 31) {
            throw new DukeException("This is an invalid day");
        }


        String hoursString = hour;
        String amOrpm = "";

        if (Integer.parseInt(hoursString.substring(0, 2)) < 12) {
            amOrpm = "am";
        } else if (Integer.parseInt(hoursString.substring(0, 2)) < 24) {
            amOrpm = "pm";
        }

        if (Integer.parseInt(hoursString.substring(0, 2)) > 23 ) {
            throw new DukeException("This is an invalid time");
        }

        String minuteString = "";

        if (Integer.parseInt(hoursString.substring(2)) == 0) {
            minuteString = "";
        } else {
            minuteString = "." + hoursString.substring(2);
        }

        if (Integer.parseInt(hoursString.substring(2)) > 59) {
            throw new DukeException("This is an invalid time");
        }

        int hourString = -1;

        if (Integer.parseInt(hoursString.substring(0, 2)) > 12) {
            hourString = (Integer.parseInt(hoursString.substring(0, 2)) - 12);
        } else {
            hourString = Integer.parseInt(hoursString.substring(0, 2));
        }

        int year = Integer.parseInt(words[2].split(" ")[0]);

        int month = Integer.parseInt(words[1]);

        if ( month-1 > 11) {
            throw new DukeException("This is an invalid month");
        }

        String[] possibleMonths = {"January", "February", "March", "April", "May", "June", "July", "August",
                                   "September", "October", "November", "December"};

        String monthString = possibleMonths[month - 1];

        return (day + dayString + " of " + monthString + " " + year + ", " + hourString + minuteString + amOrpm);
    }

    /**
     * Set the task done.
     *
     * @param stats Statistic object.
     */
    @Override
    public void setDone(Statistic stats) {
        super.setDone(stats);
        stats.incrementTotalEventsCompleted();
    }

    /**
     * Getter function for the taskType, "E".
     *
     * @return Char taskType.
     */
    public char getTaskType() {
        return 'D';
    }

}
