import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Deadline class represents on of the 3 tasks.
 * Has the date attribute to understand date and time.
 * Has description attribute to represent task description.
 */
public class Deadline extends Task {

    //private Date date;
    //private SimpleDateFormat dateFormat;
    private String by;
    protected String amOrPm;
    protected  String[] suffixes = {  "0th",  "1st",  "2nd",  "3rd",  "4th",  "5th",  "6th",  "7th",  "8th",  "9th",
                                      "10th", "11th", "12th", "13th", "14th", "15th", "16th", "17th", "18th", "19th",
                                      "20th", "21st", "22nd", "23rd", "24th", "25th", "26th", "27th", "28th", "29th",
                                      "30th", "31st" };
    protected String[] months = {  " ", "January", "February", "March", "April", "May",
                                   "June", "July", "August", "September",
                                   "October", "November", "December" };
    protected String formattedTime;
    protected String formattedDate;
    protected String formattedDateAndTime = "";

    /*/**
     * Constructor of Deadline object.
     * @param description Full description of deadline task.
     * @param date Date at which task is due.
     * @throws ParseException when date entered is invalid or input of wrong format.
     */
    /*public Deadline(String description, String date) throws ParseException {
        super(description);
        this.by = date;
        try {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
            dateFormat.setLenient(false);
            this.date = dateFormat.parse(date);
        } catch (ParseException e) {
            throw e;
        }
    }*/

    /*/**
     * Formats the the deadline object.
     * @return a string representation of deadline object.
     */
    /*@Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dateFormat.format(date));
    }*/

    /**
     * Constructor of Deadline object.
     * @param description Full description of deadline task.
     * @param by date and time of deadline
     */
    public Deadline (String description, String by) {
        super(description);
        this.by = by.trim();

        String[] arr = this.by.split(" ");
        processTime(arr[1]);
        processDate(arr[0]);

        formattedDateAndTime = formattedDate + ", " + formattedTime;

        if(!formattedDateAndTime.equals("")){
            this.by = formattedDateAndTime;
        }
    }

    /**
     * Formats the date.
     * @param dateInString date to be formatted.
     */
    public void processDate(String dateInString) {
        String[] splitDates = dateInString.split("/");
        int size = splitDates.length;
        if(size == 3) {
            String date = splitDates[0];
            String month = splitDates[1];
            String year = splitDates[2];
            int intDate = Integer.parseInt(date);
            date = suffixes[intDate];
            int intMonth = Integer.parseInt(month);
            String monthInWords = months[intMonth];
            int intYear = Integer.parseInt(year);
            formattedDate = date + " of " + monthInWords + " " + intYear;
        }
    }

    /**
     * Formats time.
     * @param timeInString time to be formatted.
     */
    public void processTime(String timeInString) {
        int time = Integer.parseInt(timeInString);
        int hour = time / 100;
        int min = time % 100;
        if (hour == 12) {
            amOrPm = "pm";
            hour = 12;
        } else if (hour > 12) {
            amOrPm = "pm";
            hour -= 12;
        } else {
            amOrPm = "am";
        }
        if(min == 0) {
            formattedTime = hour + amOrPm + "";
        } else {
            formattedTime = hour + "." + min + amOrPm;
        }
    }

    /**
     * Returns the description of deadline object.
     * @return a string representation of description.
     */
    public String getDescription() {
        return super.getDescription();
    }

    /**
     * Returns the due date of deadline object.
     * @return a string representation of date.
     */
    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by + ")";
    }

}
