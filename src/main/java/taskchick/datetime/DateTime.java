package taskchick.datetime;

import taskchick.exception.TaskChickException;

/**
 * Enables Task Chick to understand date and times.
 */
public class DateTime {

    private int year;
    private int month;
    private int day;
    private int time;

    /**
     * Initialises appropriate date and time fields for Task Chick to understand.
     *
     * @param year 4-digit integer.
     * @param month Integer value of the month.
     * @param day Integer value of the day of the month.
     * @param time 4-digit integer number (time in 24 hour format).
     */
    public DateTime(int year, int month, int day, int time) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.time = time;
    }

    public int[] getDate() {
        return new int[]{day, month, year};
    }

    /**
     * Creates a DateTime object by splitting up the date and time given in a string.
     *
     * @param dateTimeString Date and time given in the format DD/MM/YYYY HHMM.
     * @return DateTime object created.
     * @throws ArrayIndexOutOfBoundsException If date and time given is not in the accepted format.
     */
    public static DateTime create(String dateTimeString) throws TaskChickException {
        try {
            String[] currArray = dateTimeString.split("\\s+", 2);
            String dateString = currArray[0];
            String timeString = currArray[1];
            String[] dateArray = dateString.split("/", 3);
            return new DateTime(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]),
                    Integer.parseInt(dateArray[0]), Integer.parseInt(timeString));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TaskChickException("OOPS!!! Please indicate the date/time in the format DD/MM/YYYY HHMM.");
        }
    }

    /**
     * Formats the date with suffix based on the number of the day.
     *
     * @return Date with suffix -st, -nd, -rd and -th when appropriate.
     */
    private String getDay() {
        if (day == 1 || day == 21 || day == 31) {
            return day + "st";
        } else if (day == 2 || day == 22) {
            return day + "nd";
        } else if (day == 3 || day == 23) {
            return day + "rd";
        } else if (day <= 30){
            return day + "th";
        } else {
            throw new TaskChickException("OOPS!!! Please enter a valid date.");
        }
    }

    /**
     * Formats the month based on the number of the month.
     *
     * @return Month name in string format.
     */
    private String getMonth() {
        String monthString;
        switch (month) {
        case 1:
            monthString = "January";
            return monthString;
        case 2:
            monthString = "February";
            return monthString;
        case 3:
            monthString = "March";
            return monthString;
        case 4:
            monthString = "April";
            return monthString;
        case 5:
            monthString = "May";
            return monthString;
        case 6:
            monthString = "June";
            return monthString;
        case 7:
            monthString = "July";
            return monthString;
        case 8:
            monthString = "August";
            return monthString;
        case 9:
            monthString = "September";
            return monthString;
        case 10:
            monthString = "October";
            return monthString;
        case 11:
            monthString = "November";
            return monthString;
        case 12:
            monthString = "December";
            return monthString;
        default:
            throw new TaskChickException("OOPS!!! Please enter a valid month.");
        }
    }

    /**
     * Formats the 24-hour time into 12-hour time format.
     *
     * @return 12-hour time in string format.
     */
    private String getTime() {
        int hour;
        int minute;
        String timeSuffix;
        if (time <= 59) {
            minute = time % 100;
            hour = 12;
            timeSuffix = "am";
        } else if (time <= 1159) {
            minute = time % 100;
            hour = (time - minute) / 100;
            timeSuffix = "am";
        } else if (time <= 1259) {
            minute = time % 100;
            hour = 12;
            timeSuffix = "pm";
        } else if (time >= 1300 && time <= 2359) {
            minute = time % 100;
            hour = (time - minute) / 100 - 12;
            timeSuffix = "pm";
        } else {
            throw new TaskChickException("OOPS!!! Please enter a valid time.");
        }
        return hour + ":" + String.format("%02d", minute) + timeSuffix;
    }

    @Override
    public String toString() {
        return getDay() + " of " + getMonth() + " " +  year + ", " + getTime();
    }
}
