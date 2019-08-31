package duke;

import duke.exception.DukeException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Month;

class DateTimeHelper {

    private static final String[] ORDINALS = {"0th", "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th",
        "10th", "11th", "12th", "13th", "14th", "15th", "16th", "17th", "18th", "19th",
        "20th", "21st", "22nd", "23rd", "24th", "25th", "26th", "27th", "28th", "29th",
        "30th", "31st"};

    public static String convertDateToString(String dateAndTime) throws DukeException {
        String date = dateAndTime.split(" ")[0];
        String time = dateAndTime.split(" ")[1];

        return parseDate(date) + ", " + parseTime(time);
    }

    private static String parseDate(String date) throws DukeException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        try {
            format.parse(date);
        } catch (ParseException e) {
            throw new DukeException("Invalid date format");
        }

        String[] ddmmyyyy = date.split("/");
        String month = (Month.of(Integer.parseInt(ddmmyyyy[1]))).toString().toLowerCase();
        return ORDINALS[Integer.parseInt(ddmmyyyy[0])] + " of "
            + month.substring(0, 1).toUpperCase()
            + month.substring(1)
            + " " + ddmmyyyy[2];
    }

    private static String parseTime(String time) throws DukeException {
        System.out.println(time);
        if (!(time.matches("([0-1][0-9][0-5][0-9])") || time.matches("(2[0-3][0-5][0-9])"))) {
            throw new DukeException("Invalid time format, must be between 0000 and 2359");
        }

        int timeInt = Integer.parseInt(time);
        if (timeInt < 100) {
            return formatTime(timeInt + 1200, "am");
        } else if (timeInt < 1200) {
            return formatTime(timeInt, "am");
        } else if (timeInt < 1300) { // && timeInt >= 1200
            return formatTime(timeInt, "pm");
        } else { // > 1300 pm
            return formatTime(timeInt - 1200, "pm");
        }
    }

    private static String formatTime(int timeInt, String ampm) {
        if (timeInt % 100 == 0) {
            return (timeInt / 100) + ampm;
        } else {
            return (timeInt / 100) + "." + (timeInt % 100) + ampm;
        }
    }

}
