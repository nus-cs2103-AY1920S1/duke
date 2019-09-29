/**
 * This class contains methods to process Dates and Times entered into Duke.
 */

package duke.managers;

import duke.exceptions.DateException;

public class DateTime {

    private static String[] daySuffix = {
        "st", "nd", "rd", "th", "th", "th", "th", "th", "th", "th",
        "th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
        "st", "nd", "rd", "th", "th", "th", "th", "th", "th", "th", "st"
    };
    private static String[] monthName = {
        "January", "February", "March", "April",
        "May", "June", "July", "August", "September",
        "October", "November", "December"
    };

    public DateTime() {

    }

    /**
     * Returns the date in the desired convention.
     *
     * @param date a String containing the date that was in the command to Duke
     * @exception DateException if the month number is invalid
     */
    public static String getDate(String date) throws DateException {
        String[] splitDate = date.split("/");
        String dd = addDaySuffix(splitDate[0]) + " of ";
        String mm = wordMonth(splitDate[1]) + " ";
        String yy = splitDate[2] + ", ";
        return dd + mm + yy;
    }

    /**
     * Adds the suffix for the day in the date String. It is used in the getDate method.
     *
     * @param day a String containing the day number
     */
    private static String addDaySuffix(String day) {
        int dayNum = Integer.parseInt(day);
        return day + daySuffix[dayNum - 1];
    }

    /**
     * Changes the month number to its name in the date String. It is used in the getDate method.
     *
     * @param month a String containing the month number
     * @exception DateException if the month number is invalid
     */
    private static String wordMonth(String month) throws DateException {
        int monthNum = Integer.parseInt(month);
        if (monthNum > 12) {
            throw new DateException("Invalid month entered!");
        } else {
            return monthName[monthNum - 1];
        }
    }

    /**
     * Returns the time in the desired convention.
     *
     * @param time a String containing the time in military format
     * @exception DateException if the month number is invalid
     */
    public static String getTime(String time) throws DateException {
        assert time.length() == 4 : "Time invalid";
        String hours = time.substring(0,2);
        String timeString = getHours(hours) + getMinutes(time.substring(2,4));
        if (Integer.parseInt(hours) < 12) {
            return timeString + "am";
        } else {
            return timeString + "pm";
        }
    }

    /**
     * Returns the hour component of time. It is used in the getTime method.
     *
     * @param hours a String containing the hour number
     * @exception DateException if the month number is invalid
     */
    private static String getHours(String hours) throws DateException {
        int hourNum = Integer.parseInt(hours);
        if (hourNum > 24) {
            throw new DateException("Invalid time entered!");
        } else {
            hourNum = hourNum % 12;
            if (hourNum == 0) {
                return "12";
            } else {
                return hourNum + "";
            }
        }
    }

    /**
     * Returns the minute component of time. It is used in the getTime method.
     *
     * @param minutes a String containing the minute number
     * @exception DateException if the month number is invalid
     */
    private static String getMinutes(String minutes) throws DateException {
        int minNum = Integer.parseInt(minutes);
        if (minNum > 60) {
            throw new DateException("Invalid time entered!");
        } else if (minNum > 0) {
            return "." + minutes;
        } else {
            return "";
        }
    }
}
