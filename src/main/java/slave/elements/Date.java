package slave.elements;

import slave.exception.DukeException;
import slave.exception.InvalidDateException;

public class Date {

    String dateFormat;
    String time;
    String timeFormat;
    String hour;
    String minute;

    int day;
    int month;
    int year;

    static String[] timeSuffixes = {"am", "am", "am", "am", "am", "am", "am", "am", "am", "am", "am", "am",
            "pm", "pm", "pm", "pm", "pm", "pm", "pm", "pm", "pm", "pm", "pm", "pm"};
    static String[] timeHourSuffixes = {"12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
            "12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
    static String[] daySuffixes = { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
            "th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
            "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
            "th", "st" };
    static String[] monthString = { "Placeholder", "January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November", "December" };

    public Date(String dateFormat, String time) throws DukeException {
        this.dateFormat = dateFormat;
        this.time = time;
        format();
    }

    void format() throws DukeException {
        try {
            String[] splitDate = this.dateFormat.split("/");
            this.day = Integer.parseInt(splitDate[0]);
            this.month = Integer.parseInt(splitDate[1]);
            this.year = Integer.parseInt(splitDate[2]);
            this.hour = time.substring(0, 2);
            this.minute = time.substring(2);
            int indexHour = Integer.parseInt(this.hour);
            this.timeFormat = timeHourSuffixes[indexHour] + "." + this.minute + timeSuffixes[indexHour];
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
