package duke.formats;

import java.text.DateFormatSymbols;

/**
 * Represents the Date and Time.
 */

public class DateTime {

    private String day;
    private String month;
    private String year;
    private String time;
    private String meridiem;

    /**
     * Constructs the DateTime object from a formatted String.
     *
     * 
     * @param toDateTime formatted string representing date and time
     */

    public DateTime(String toDateTime) {
        String[] dateAndTime = toDateTime.split(" ");
        String date = dateAndTime[0];
        String[] dayMonthYear = date.split("/");
        int dayInt = Integer.valueOf(dayMonthYear[0]);
        setDay(dayInt);
        int monthInt = Integer.valueOf(dayMonthYear[1]);
        this.month = new DateFormatSymbols().getMonths()[monthInt - 1];
        this.year = dayMonthYear[2];
        String time = dateAndTime[1];
        int timeInt = Integer.valueOf(time);
        setTime(timeInt);
    }

    /**
     * Sets the day field of DateTime based on the day of the month
     * provided.
     *
     * @param dayInt Day of the month as an integer
     */

    private void setDay(int dayInt) {
        assert dayInt <= 31 : "day int can only go up to 31";
        switch (dayInt) {
        case 1:
            this.day = "1st";
            break;
        case 2:
            this.day = "2nd";
            break;
        case 3:
            this.day = "3rd";
            break;
        default:
            this.day = dayInt + "th";
        }
    }

    /**
     * Sets the time of the DateTime object based on the timeInt provided.
     * @param timeInt time of the day in integer form
     */

    private void setTime(int timeInt) {
        this.meridiem = timeInt >= 1200 ? "pm" : "am";
        int minutes = timeInt % 100;
        int twentyFourHour = (timeInt - minutes) / 100;
        int hours = twentyFourHour % 12 == 0 ? 12 : twentyFourHour % 12;
        this.time = hours + "." + String.format("%02d", minutes);
    }

    /**
     * Returns a formatted String based on the required DateTime format.
     * 
     * @return formatted String
     */
    
    @Override
    public String toString() {
        return " " + day + " of " + month + " " + year + ", " + time + meridiem;
    }
}
