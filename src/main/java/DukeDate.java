package duke.date;

import java.text.DateFormatSymbols;

/**
 * Represents a date that can be recognized by the program.
 */
public class DukeDate {
    private String dateString;

    /**
     * Creates a DukeDate object by taking in a string of format dd/mm/yyyy hhmm.
     * 
     * @param dateString a string of the format dd/mm/yyyy hhmm
     */
    public DukeDate(String dateString) {
        this.dateString = dateString;
    }

    private String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month - 1];
    }

    private String getDate(int date) {
        String output;
        switch (date % 10) {
        case 1:
            output = "st";
            break;
        case 2:
            output = "nd";
            break;
        case 3:
            output = "rd";
            break;
        default:
            output = "th";
        }
        return date + output;
    }

    private String getTime(int time) {
        String output;

        if (time >= 1200) {
            output = "pm";
        } else {
            output = "am";
        }

        if (time < 100) {
            output = String.format("12.%02d", time) + output;
        } else {
            if (time > 1300) {
                time = time - 1200;
            }
            output = String.format("%d.%02d", (time / 100), (time % 100)) + output;
        }
        return output;

    }

    /**
     * Returns the date object as a string object in the form (date of month year,
     * hour min).
     */
    public String toString() {
        String[] dateComponent = dateString.split("/| ");
        int date = Integer.parseInt(dateComponent[0]);
        int month = Integer.parseInt(dateComponent[1]);
        String year = dateComponent[2];
        int time = Integer.parseInt(dateComponent[3]);

        return String.format("%s of %s %s, %s", getDate(date), getMonth(month), year, getTime(time));
    }
}