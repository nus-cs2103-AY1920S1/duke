package duke.datetime;

import duke.Duke;
import duke.dukeexception.DukeException;
import static duke.dukeexception.DukeException.*;
/**
 * Represents a date in the format Day/Month/Year.
 */
public class Date {
    private int day;
    private int month;
    private int year;
    private String dateString;
    private static String[] MONTHS = {"January", "February", "March", "April",
        "May", "June", "July", "August",
        "September", "October", "November", "December"};

    /**
     * Class constructor that specifies the String to represent as a
     * Date object.
     *
     * @param dateString String to parse into Date.
     */
    public Date(String dateString) throws DukeException {
        try {
            this.dateString = dateString;
            String[] tokens = dateString.split("/");
            this.day = Integer.parseInt(tokens[0]);
            this.month = Integer.parseInt(tokens[1]);
            this.year = Integer.parseInt(tokens[2]);

            if(day > 31) {
                throw new DukeException(DATETIME_ERROR);
            }
            if(month > 12 || month  < 1) {
                throw new DukeException(DATETIME_ERROR);
            }

        } catch (Exception de) {
            throw new DukeException(DATETIME_ERROR);
        }
    }

    /**
     * Returns the initial String representation before parsing.
     *
     * @return Initial String before it was parsed.
     */
    public String getDateString() {
        return dateString;
    }

    /**
     * Returns String representation of the Date object, using the names of the Months.
     *
     * @return String representation of this Date object in English.
     */
    @Override
    public String toString() {
        String toReturn;
        if (day % 10 == 2 && day != 12) {
            toReturn = day + "nd";
        } else if (day % 10 == 3 && day != 13) {
            toReturn = day + "rd";
        } else if (day % 10 == 1 && day != 11) {
            toReturn = day + "st";
        } else {
            toReturn = day + "th";
        }
        toReturn = toReturn + " of " + MONTHS[month - 1] + " " + year;
        return toReturn;
    }

}
