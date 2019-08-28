/**
 * Represents the date time of user input in proper format.
 * @author Fabian Chia Hup Peng
 */

public class DateTime {

    private static final String[] months = {"DummyMonth", "January", "February", "March",
            "April", "May", "June", "July", "August", "September", "October", "November",
            "December"};

    private String day;
    private String month;
    private String year;
    private String time;

    /**
     * Creates a DateTime instance with the appropriate attributes.
     * @param day The day.
     * @param month The month.
     * @param year The year.
     * @param time The time.
     */
    public DateTime(String day, String month, String year,
                            String time) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.time = time;

    }

    /**
     * Formats the user input into proper representation.
     */
    public void format() {
        formatDay();
        formatMonth();
        formatTime();
    }

    /**
     * Formats the day into proper representation.
     */
    private void formatDay() {
        if(day.equals("01") || day.equals("1") || day.equals("21") || day.equals("31")) {
            day += "st";
        } else if(day.equals("02") || day.equals("2") || day.equals("22")) {
            day += "nd";
        } else if(day.equals("03") || day.equals("3") || day.equals("23")) {
            day += "rd";
        } else {
            day += "th";
        }
    }

    /**
     * Formats the month into proper representation.
     */
    private void formatMonth() {
        month = months[Integer.parseInt(month)];
    }

    /**
     * Formats the time into proper representation.
     */
    private void formatTime() {

        int twentyFourHourTime = Integer.parseInt(time);

        int hours = twentyFourHourTime / 100;
        int minutes = twentyFourHourTime % 100;

        int convertedHours = hours % 12;

        if(convertedHours == 0) {
            convertedHours = 12;
        }

        String meridian;

        if(hours < 12) {
            meridian = "am";
        } else {
            meridian = "pm";
        }

        if(minutes == 0) {
            time = convertedHours + meridian;
        } else {
            time = convertedHours + ":" + minutes + meridian;
        }
    }

    /**
     * Returns a string representation of a DateTime object.
     * @return The day, month, year and time formatted into proper
     * representation.
     */
    @Override
    public String toString() {
        return day + " of " + month + " " + year + ", " + time;
    }

}
