package duke.task;

/**
 * Represents an Task object of type Deadline. A <code>Deadline</code> object
 * specifies when it is to be completed by and its item description.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Creates a Task of type Deadline and formats deadline if possible.
     *
     * @param description Description information about the Deadline object.
     * @param by The timeline to complete the deadline task by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        humanizeDeadline();
    }

    /**
     * Humanized deadline of a Deadline object if deadline matches specified date format dd/mm/yyyy hhmm.
     *
     */
    //Regex below adapted from https://stackoverflow.com/questions/23360599/regular-expression-for-dd-mm-yyyy-hhmm
    public void humanizeDeadline() {
        String pat = "^([1-9]|([012][0-9])|(3[01]))/([0]{0,1}[1-9]|1[012])/\\d\\d\\d\\d [012]{0,1}[0-9][0-6][0-9]$";
        if (by.matches(pat)) {
            String hoursMin = by.split(" ")[1].trim();
            String[] dayMonthYear = by.split(" ")[0].trim().split("/");
            String day = formatDate(dayMonthYear[0]);
            String month = formatMonth(dayMonthYear[1]);
            String year = dayMonthYear[2];
            String time = formatHour(hoursMin);
            by = String.format("%s of %s %s, %s", day, month, year, time);
        }
    }

    private String formatDate(String date) {
        if (date.equals("1")) {
            return "1st";
        } else if (date.equals("2")) {
            return "2nd";
        } else if (date.equals("3")) {
            return "3rd";
        } else if (date.equals("21")) {
            return "21st";
        } else if (date.equals("22")) {
            return "22nd";
        } else if (date.equals("23")) {
            return "23rd";
        } else if (date.equals("31")) {
            return "31st";
        } else {
            return date + "th";
        }
    }

    private String formatHour(String hour) {
        if (Integer.valueOf(hour) <= 1200) {
            return String.format("%s.%s%s", hour.substring(0,2), hour.substring(2), "am");
        } else {
            if (Integer.valueOf(hour) < 1300) {
                return String.format("%s.%.s%d", hour.substring(0,2), hour.substring(3), "pm");
            } else {
                return String.format("%s.%s%s", Integer.valueOf(hour.substring(0,2)) - 12, hour.substring(2), "pm");
            }
        }
    }

    private String formatMonth(String month) {
        switch (month) {
        case "1" :
            return "January";
        case "2" :
            return "February";
        case "3" :
            return "March";
        case "4" :
            return "April";
        case "5" :
            return "May";
        case "6" :
            return "June";
        case "7" :
            return "July";
        case "8" :
            return "August";
        case "9" :
            return "September";
        case "10" :
            return "October";
        case "11" :
            return "November";
        case "12" :
            return "December";
        default:
            return "Invalid Date";
        }
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "D " + super.toString() + "| " + by;
    }
}

