import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Deadline class represents on of the 3 tasks.
 * Has the date attribute to understand date and time.
 * Has description attribute to represent task description.
 */
public class Deadline extends Task {

    private Date date;
    private SimpleDateFormat dateFormat;
    private String by;


    //protected String date;
    protected String month;
    protected String monthInWords;
    protected String year;
    protected String timeInString;
    protected int hour;
    protected int min;
    protected int time;
    protected int intDate;
    protected int intMonth;
    protected int intYear;
    protected String amOrPm;
    protected  String[] suffixes =
            {  "0th",  "1st",  "2nd",  "3rd",  "4th",  "5th",  "6th",  "7th",  "8th",  "9th",
                    "10th", "11th", "12th", "13th", "14th", "15th", "16th", "17th", "18th", "19th",
                    "20th", "21st", "22nd", "23rd", "24th", "25th", "26th", "27th", "28th", "29th",
                    "30th", "31st" };
    protected String[] months =
            {  " ", "January", "February", "March", "April", "May", "June", "July", "August", "September",
                    "October", "November", "December" };
    protected String formattedTime;
    protected String formattedDate;
    protected String formattedDateAndTime = "";

    /**
     * Constructor of Deadline object.
     * @param description Full description of deadline task.
     * @param date Date at which task is due.
     * @throws ParseException when date entered is invalid or input of wrong format.
     */
    public Deadline (String description, String date) throws ParseException {
        super(description);
        this.by = date;
        try {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
            dateFormat.setLenient(false);
            this.date = dateFormat.parse(date);
        } catch (ParseException e) {
            throw e;
        }
    }

    /*public Date getDate() {
        return date;
    }/*

    /**
     * Formats the the deadline object.
     * @return a string representation of deadline object.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), dateFormat.format(date));
    }

    /*public Deadline (String description, String by) {
        super(description);
        this.by = by.trim();
        //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        //tasks.addItemsToList(new Deadline(c.getInstruction), formatter

        String[] arr = this.by.split(" ");
        timeInString = arr[2];
        time = Integer.parseInt(timeInString);
        hour = time / 100;
        min = time % 100;
        if(hour >= 13) {
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

        String[] splitDates = arr[1].split("/");
        int size = splitDates.length;
        if(size == 3) {
            date = splitDates[0];
            month = splitDates[1];
            year = splitDates[2];
            intDate = Integer.parseInt(date);
            date = suffixes[intDate];
            intMonth = Integer.parseInt(month);
            monthInWords = months[intMonth];
            intYear = Integer.parseInt(year);
            formattedDate = date + " of " + monthInWords + " " + intYear;
            formattedDateAndTime = formattedDate + ", " + formattedTime;
        }

        if(!formattedDateAndTime.equals("")){
            this.by = formattedDateAndTime;
        }
    }*/

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

    /*@Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }*/

    /*public static Deadline outputAsDeadline(String lineToRead) {
        String[] descriptionNDate = lineToRead.split(",");
        String description = descriptionNDate[0];
        String by = descriptionNDate[1];
        return new Deadline(description, by);
    }*/
}
