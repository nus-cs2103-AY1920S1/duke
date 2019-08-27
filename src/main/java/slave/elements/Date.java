package slave.elements;

import slave.exception.DukeException;
import slave.exception.InvalidDateException;

public class Date {

    private String dateFormat;
    private String time;
    private String timeFormat;

    private int day;
    private int month;
    private int year;

    private static String[] timeSuffixes = {"am", "am", "am", "am", "am", "am", "am", "am", "am", "am", "am", "am",
            "pm", "pm", "pm", "pm", "pm", "pm", "pm", "pm", "pm", "pm", "pm", "pm"};

    private static String[] timeHourSuffixes = {"12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
            "12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};

    private static String[] daySuffixes = { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
            "th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
            "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
            "th", "st" };

    private static String[] monthString = { "Placeholder", "January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November", "December" };

    public Date(String dateFormat, String time) throws DukeException {
        this.dateFormat = dateFormat;
        this.time = time;
        format();
    }

    private void format() throws DukeException {
        try {
            String[] splitDate = this.dateFormat.split("/");

            this.day = Integer.parseInt(splitDate[0]);
            this.month = Integer.parseInt(splitDate[1]);
            this.year = Integer.parseInt(splitDate[2]);

            String hour = time.substring(0, 2);
            String minute = time.substring(2);

            int indexHour = Integer.parseInt(hour);
            this.timeFormat = timeHourSuffixes[indexHour] + "." + minute + timeSuffixes[indexHour];

        } catch (ArrayIndexOutOfBoundsException e){
            throw new InvalidDateException();
        }
    }

    public String convertToString() throws DukeException {
        String result;
        try {
            result = this.day + daySuffixes[this.day] +
                    " of " + monthString[this.month] +
                    " " + this.year + ", " + timeFormat;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidDateException();
        }
        return result;
    }
}
