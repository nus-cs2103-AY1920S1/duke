/**
 * This class processes date input from the user. For example, if the command is deadline return book /by
 * 2/12/2019 1800, Duke understands 2/12/2019 1800 as 2nd of December 2019, 6pm, instead of storing it simply
 * as a String.
 */
public class DateTime {

    /**
     * Original input from user.
     */
    String dateTime;

    /**
     * Processed day from user input.
     */
    String day;

    /**
     * Processed month from user input.
     */
    String month;

    /**
     * Processed year from user input.
     */
    String year;

    /**
     * Processed time from user input.
     */
    String time;

    /**
     * Class constructor that assigns user input date and time to the instance.
     * @param dateTime Date and time input form user
     */
    public DateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * This method splits the user input into day, month and year.
     * @param d Date from user input
     */
    public void processDate(String d) {
        String[] splitDate = d.split("/");
        processDay(splitDate[0]);
        processMonth(splitDate[1]);
        year = splitDate[2];
    }

    /**
     * Process day into desired format.
     * @param d Unformatted day input
     */
    public void processDay(String d) {
        int dayNum = Integer.parseInt(d);
        if (dayNum >= 11 && dayNum <= 13) {
            day = d + "th";
        } else if (dayNum % 10 == 1) {
            day = d + "st";
        } else if (dayNum % 10 == 2) {
            day = d + "nd";
        } else if (dayNum % 10 == 3) {
            day = d + "rd";
        } else {
            day = d + "th";
        }
    }

    /**
     * Process month into desired format.
     * @param m Unformatted month input
     */
    public void processMonth(String m) {
        switch (Integer.parseInt(m)) {
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
        }
    }

    /**
     * Process time into desired format.
     * @param t Unformatted time input
     */
    public void processTime(String t) {
        int timeNum = Integer.parseInt(t);
        int minutes = timeNum % 100;
        if (minutes > 0) {
            if (timeNum < 59) {
                int hour = 12;
                time = String.valueOf(hour) + ":" + String.format("%02d", minutes) + "am";
            } else if (timeNum < 1300) {
                // time before 1pm
                int hour = (timeNum - minutes) / 100;
                if (hour < 12) {
                    time = String.valueOf(hour) + ":" + String.format("%02d", minutes) + "am";
                } else {
                    time = String.valueOf(hour) + ":" + String.format("%02d", minutes) + "pm";
                }
            } else {
                // 1pm and after until 2359
                int hour = ((timeNum - minutes) / 100) - 12;
                time = String.valueOf(hour) + ":" + String.format("%02d", minutes) + "pm";
            }
        } else {
            if (timeNum < 59) {
                int hour = 12;
                time = String.valueOf(hour) + "am";
            } else if (timeNum < 1300) {
                // time before 1pm
                int hour = (timeNum - minutes) / 100;
                if (hour < 12) {
                    time = String.valueOf(hour) + "am";
                } else {
                    time = String.valueOf(hour) + "pm";
                }
            } else {
                // 1pm and after until 2359
                int hour = ((timeNum - minutes) / 100) - 12;
                time = String.valueOf(hour) + "pm";
            }
        }
    }

    /**
     * Getter to retrieve the time
     * @return String Time in desired format
     */
    public String getTime() {
        return time;
    }

    /**
     * Returns date and time in desired format.
     * @return String Formatted date and time
     */
    @Override
    public String toString() {
        String[] splitDateTime = dateTime.split(" ");
        processDate(splitDateTime[0]);
        processTime(splitDateTime[1]);
        String result = day + " of " + month + " " + year + ", " + time;
        return result;
    }
}
