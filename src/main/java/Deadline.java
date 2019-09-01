/**
 * Creates a Deadline object which extends from the Task class.
 * A <code>description</code> is passed into this class to
 * instantiate a Deadline task.
 */
public class Deadline extends Task {
    protected String by;
    protected String[] deadline;
    protected String[] datetime;

    /**
     * Instantiate a Events object by passing a String of description and time
     * @param description Description of the deadline task.
     * @param by The date and time of the deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        deadline = by.split(" ", 2);
        this.datetime = deadline[1].split(" ");
    }

    /**
     * Get the date from a numeric format eg. (23/05/2019) and convert it
     * into words (23rd of May 2019).
     * @return a String consisting the date in wording format
     */
    private String getdate() {
        String[] splitdates = this.datetime[0].split("/");
        String day = splitdates[0];
        String month = splitdates[1];
        String year = splitdates[2];
        String editedday;
        String editedmonth;

        if (day.equals("1") || day.equals("01")) {
            editedday = "1st";
        } else if (day.equals("2") || day.equals("02")) {
            editedday = "2nd";
        } else if (day.equals("3") || day.equals("03")) {
            editedday = "3rd";
        } else if (day.equals("21")) {
            editedday = "21st";
        } else if (day.equals("22")) {
            editedday = "22nd";
        } else if (day.equals("23")) {
            editedday = "23rd";
        } else if (day.equals("31")) {
            editedday = "31st";
        } else {
            editedday = day + "th";
        }

        switch (month) {
            case "1":
            case "01":
                editedmonth = "January";
                break;

            case "2":
            case "02":
                editedmonth = "Febuary";
                break;

            case "3":
            case "03":
                editedmonth = "March";
                break;

            case "4":
            case "04":
                editedmonth = "April";
                break;

            case "5":
            case "05":
                editedmonth = "May";
                break;

            case "6":
            case "06":
                editedmonth = "June";
                break;

            case "7":
            case "07":
                editedmonth = "July";
                break;

            case "8":
            case "08":
                editedmonth = "August";
                break;

            case "9":
            case "09":
                editedmonth = "September";
                break;

            case "10":
                editedmonth = "October";
                break;

            case "11":
                editedmonth = "November";
                break;

            case "12":
                editedmonth = "December";
                break;

            default:
                editedmonth = "Invalid";
                break;

        }

        return editedday + " of " + editedmonth + " " + year;
    }

    /**
     * Get the time format from 24Hr eg.(2300) to a 12Hr HH:MM format
     * eg.(11.00pm).
     * @return Time in 12Hr HH:MM format.
     */
    private String gettime() {
        String time = this.datetime[1];
        String timestr;
        int timeint = Integer.parseInt(time);
        int hours = timeint/100;
        int minutes = timeint % 100;
        if (hours >= 12) {
            timestr = Integer.toString(hours - 12) + "."
                    + String.format("%02d", minutes) + "pm";
        } else if (hours == 0) {
            timestr = "12."
                    + String.format("%02d", minutes) + "am";
        }
        else {
            timestr = Integer.toString(hours) + "."
                    + String.format("%02d", minutes) + "am";
        }

        return timestr;
    }

    @Override
    public String formatString() {
        return "D-" + super.checkStatus() + "-" + super.getDescription().trim() + "-" + this.deadline[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                "(by: " + this.getdate() + ", " + this.gettime() + ")";
    }
}
