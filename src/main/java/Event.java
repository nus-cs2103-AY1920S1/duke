import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Event class represents one of the 3 Tasks.
 */
public class Event extends Task {

    private String at;
    //private Date dateAndTime;
    //private SimpleDateFormat dateFormat;
    //private String dateAndTime;
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

    /**
     * Constructor of Event object.
     * @param description task description.
     * @param dateAndTime dateAndTime in which event is going to take place.
     * @throws IllegalArgumentException Invalid dateAndTime.
     * @throws ParseException invalid dateAndTime.
     */
    /*public Event(String description, String dateAndTime) throws IllegalArgumentException, ParseException {
        super(description);
        this.at = dateAndTime;
        try {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
            dateFormat.setLenient(false);
            this.dateAndTime = dateFormat.parse(dateAndTime);
        } catch (ParseException e) {
            throw e;
        }
    }*/

    /**
     * Constructor of Event object.
     * @param description Full description of deadline task.
     * @param at dateAndTime in which event is going to take place.
     */
    public Event (String description, String at) {
        super(description);
        this.at = at.trim();

        String[] arr = this.at.split(" ");
        processTime(arr[1]);
        processDate(arr[0]);

        formattedDateAndTime = formattedDate + ", " + formattedTime;

        if(!formattedDateAndTime.equals("")){
            this.at = formattedDateAndTime;
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

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.at + ")";
    }

    /**
     * Returns string representation of dateAndTime.
     * @returni at.
     */
    public String getAt() {
        return at;
    }

    /*/**
     * Returns string representation of Event object.
     * @return string represenation of Event object.
     */
    /*@Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), dateFormat.format(dateAndTime));
    }*/

    /**
     * Returns description of Event task.
     * @return description.
     */
    public String getDescription() {
        return super.getDescription();
    }
}