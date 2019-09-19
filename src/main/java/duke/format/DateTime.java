package duke.format;

import duke.exception.*;

/**
 * Teaching Duke to understand dates and times.
 */
public class DateTime {

    String dateTime;
    String day;
    String month;
    String year;
    String time;

    /**
     * Initialises a DateTime.
     * Ensures no white spaces at front and back of string before reformatting it.
     *
     * @param dateTime raw string extracted from original user input string.
     */
    public DateTime(String dateTime) {
        this.dateTime = dateTime.strip();
    }

    /**
     * Converts original dateTime string to a different format.
     * eg. Converts 2/12/2019 1800 -> 2nd of December 2019, 6pm.
     *
     * @return reformatted string.
     * @throws DukeException if invalid dateTime given.
     */
    public String toReformat() throws DukeException {
        reformat(dateTime);
        return " " + day + " of " + month + " " + year + ", " + time;
    }

    public void reformat(String dateTime) throws DukeException {
        String[] splitStr = dateTime.split("/", 3);
        if (splitStr.length < 3) {
            throw new InvalidDateTimeException();
        } else {
            reformatDay(splitStr[0]);
            reformatMonth(splitStr[1]);
            String[] splitTime = splitStr[2].split(" ", 2);
            if (splitTime.length < 2) {
                throw new InvalidDateTimeException();
            } else {
                reformatYear(splitTime[0]);
                reformatTime(splitTime[1]);
            }
        }
    }

    public void reformatDay(String split) throws DukeException {
        int i = Integer.parseInt(split);
        if (i > 31 || i < 1) {
            throw new InvalidDateTimeException();
        } else if (i == 11 || i == 12 || i == 13) {
            day = split + "th";
        } else if (i % 10 == 1) {
            day = split + "st";
        } else if (i % 10 == 2) {
            day = split + "nd";
        } else if (i % 10 == 3) {
            day = split + "rd";
        } else {
            day = split + "th";
        }
    }

    public void reformatMonth(String split) throws DukeException {
        int i = Integer.parseInt(split);
        switch (i) {
        case 1:
            month = "January";
            break;
        case 2:
            month = "February";
            break;
        case 3:
            month = "March";
            break;
        case 4:
            month = "April";
            break;
        case 5:
            month = "May";
            break;
        case 6:
            month = "June";
            break;
        case 7:
            month = "July";
            break;
        case 8:
            month = "August";
            break;
        case 9:
            month = "September";
            break;
        case 10:
            month = "October";
            break;
        case 11:
            month = "November";
            break;
        case 12:
            month = "December";
            break;
        default:
            throw new InvalidDateTimeException();
        }
    }

    public void reformatYear(String split) {
        year = split;
    }

    public void reformatTime(String split) throws DukeException{
        int t = Integer.parseInt(split);
        int min = t % 100;
        int hr = t / 100;
        if (t > 2359) {
            throw new InvalidDateTimeException();
        } else if (hr == 0) {
            if (min == 0) {
                time = "12am";
            } else if (min <= 9) {
                time = "12:0" + min + "am";
            } else {
                time = "12:" + min + "am";
            }
        } else if (hr < 12) {
            if (min == 0) {
                time = hr + "am";
            } else if (min <= 9) {
                time = hr + ":0" + min + "am";
            } else {
                time = hr + ":" + min + "am";
            }
        } else if (hr == 12) {
            if (min == 0) {
                time = "12pm";
            } else if (min <= 9) {
                time = hr + ":0" + min + "pm";
            } else {
                time = hr + ":" + min + "pm";
            }
        } else {
            hr = hr - 12;
            if (min == 0) {
                time = hr + "pm";
            } else if (min <= 9) {
                time = hr + ":0" + min + "pm";
            } else {
                time = hr + ":" + min + "pm";
            }
        }
    }

}
