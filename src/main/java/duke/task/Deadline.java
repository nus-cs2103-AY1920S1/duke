package duke.task;

import duke.command.Parser;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Represents a Deadline object that contains a description and time by which it must be completed.
 */
public class Deadline extends Task {
    private Calendar time;

    /**
     * Creates a Deadline object using information from the arrayList of the Parser.
     * @param parser parser which is used to get information to make the obejct.
     */
    public Deadline(Parser parser) {
        super(parser.getList().get(0));
        time = Calendar.getInstance();
        ArrayList<String> inputArray = parser.getList();
        time.set(Integer.parseInt(inputArray.get(3)),
                (Integer.parseInt(inputArray.get(2)) - 1),
                Integer.parseInt(inputArray.get(1)),
                Integer.parseInt(inputArray.get(4)),
                Integer.parseInt(inputArray.get(5)));
    }

    /**
     * Creates a deadline object using description and time at which Deadline to be done by.
     * @param des string to represent description of the Task.
     * @param time time that deadline must be completed by.
     */
    public Deadline(String des, Calendar time) {
        super(des);
        this.time = time;
    }

    /**
     * Return time by which deadline to be completed.
     * @return time by which deadline should be completed.
     */
    public Calendar getTime() {
        return time;
    }

    /**
     * Prints time of the deadline in a user-friendly readable format.
     * @return string representing time of the deadline in a particular format.
     */
    public String printTime() {
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
        String nameOfMonth = "";
        switch (time.get(Calendar.MONTH)) {
        case 0 : nameOfMonth = "January";
            break;
        case 1 : nameOfMonth = "February";
            break;
        case 2 : nameOfMonth = "March";
            break;
        case 3 : nameOfMonth = "April";
            break;
        case 4 : nameOfMonth = "May";
            break;
        case 5 : nameOfMonth = "June";
            break;
        case 6 : nameOfMonth = "July";
            break;
        case 7 : nameOfMonth = "August";
            break;
        case 8 : nameOfMonth = "September";
            break;
        case 9 : nameOfMonth = "October";
            break;
        case 10 : nameOfMonth = "November";
            break;
        case 11 : nameOfMonth = "December";
            break;
        default : nameOfMonth = "Invalid Month";
        }
        String hoursAndMinutes = "";
        if (time.get(Calendar.HOUR_OF_DAY) <= 12) {
            hoursAndMinutes += time.get(Calendar.HOUR_OF_DAY) + ":";
        } else {
            hoursAndMinutes += time.get(Calendar.HOUR) + ":";
        }
        if (time.get(Calendar.MINUTE) < 10) {
            hoursAndMinutes += "0";
        }
        hoursAndMinutes += time.get(Calendar.MINUTE);
        if (time.get(Calendar.AM_PM) == 0) {
            hoursAndMinutes += "am";
        } else {
            hoursAndMinutes += "pm";
        }
        return time.get(Calendar.DAY_OF_MONTH) + jargon
                + nameOfMonth + ", "
                + time.get(Calendar.YEAR) + ". "
                + hoursAndMinutes;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.printTime() +  ")";
    }
}
