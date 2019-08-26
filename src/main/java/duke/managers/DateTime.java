package duke.managers;
import duke.exceptions.*;
public class DateTime {

    public DateTime() {}

    /*
    This method returns the date in the desired convention.
     */
    public static String getDate(String date) throws DateException {
        String[] ddmmyy = date.split("/");
        String dd = addDaySuffix(ddmmyy[0]) + " of ";
        String mm = wordMonth(ddmmyy[1]) + " ";
        String yy = ddmmyy[2] + ", ";
        return dd + mm + yy;
    }

    /*
    This method adds the suffix for the day in the date String. This method is used in the getDate method.
     */
    public static String addDaySuffix(String day) {
        int dayNum = Integer.parseInt(day);
        String dayWithSuffix = day;
        if (dayNum == 1) {
            dayWithSuffix += "st";
        } else if (dayNum == 2) {
            dayWithSuffix += "nd";
        } else if (dayNum == 3) {
            dayWithSuffix += "rd";
        } else {
            dayWithSuffix += "th";
        }
        return dayWithSuffix;
    }

    /*
    This method changes the month number to its name in the date String. This method is used in the getDate method.
     */
    public static String wordMonth(String month) throws DateException {
        int monthNum = Integer.parseInt(month);
        switch (monthNum) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                throw new DateException("Invalid month entered!");
        }
    }

    /*
    This method returns the time in the desired convention.
     */
    public static String getTime(String time) throws DateException {
        String timeString = "";
        String hours = time.substring(0, 2);
        timeString += getHours(hours);
        String minutes = time.substring(2,4);
        timeString += getMinutes(minutes);
        if (Integer.parseInt(hours) < 12) {
            timeString += "am";
        } else {
            timeString += "pm";
        }
        return timeString;
    }

    /*
    This method returns the hour component of time. This method is used in the getTime method.
     */
    public static String getHours(String hours) throws DateException {
        int hourNum = Integer.parseInt(hours);
        String numHour;
        if (hourNum > 24) {
            throw new DateException("Invalid time entered!");
        } else if (hourNum < 12) {
            if (hourNum == 0) {
                numHour = "12";
            } else if (hourNum < 10) {
                numHour = hours.substring(1,2);
            } else {
                numHour = hours;
            }
        } else {
            numHour = hourNum%12 + "";
        }
        return numHour;
    }

    /*
    This method returns the minute component of time. This method is used in the getTime method.
     */
    public static String getMinutes(String minutes) throws DateException {
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
