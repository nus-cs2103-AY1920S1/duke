package datetime;

import exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Wrapper of LocalDateTime. Stores date and time for Task.
 */
public class DateTime{
    private LocalDateTime start;
    private LocalTime endTime;
    private String display;
    private static DateTimeFormatter getStart = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static DateTimeFormatter getTime = DateTimeFormatter.ofPattern("HHmm");

    /**
     * Called during construction of Deadline object.
     * Reads substring from user input and gets date and time of task.
     * @param date_time Must be in the form (d/M/yyyy HHmm)
     * @return DateTime object as dateTime parameter of Deadline object.
     * @throws DukeException thrown if date_time is not presented in the correct form.
     */
    public static DateTime setDeadline(String date_time) throws DukeException{
        try {
            DateTime deadline = new DateTime();
            deadline.start = LocalDateTime.parse(date_time, getStart);
            deadline.display = deadline.start.format(getStart);
            return deadline;
        } catch (DateTimeParseException e){
            throw new DukeException("Incorrect Deadline date/time format." + System.lineSeparator() +
                    "    Please key in date(d/M/yyyy) time(HHmm)");
        }
    }

    /**
     * Called during construction of Deadline object.
     * Reads substring from text file and gets date and time of the task
     * @param date_time Must be in the form (d/M/yyyy HHmm).
     * @return DateTime object as dateTime parameter of Deadline object.
     */
    public static DateTime readDeadLine(String date_time){
        DateTime deadline = new DateTime();
        deadline.start = LocalDateTime.parse(date_time, getStart);
        deadline.display = deadline.start.format(getStart);
        return deadline;
    }

    /**
     * Called during construction of Event object.
     * Reads substring from user input and gets date, start time and end time of task.
     * @param date_time Must be in the form (d/MM/yyyy HHmm-HHmm)
     * @return DateTime object as dateTime parameter of Event object.
     * @throws DukeException thrown if missing end time or if date_time is not presented in the correct form.
     */
    public static DateTime setEventTime (String date_time) throws DukeException{
        try {
            DateTime event = new DateTime();
            int divider = date_time.indexOf("-");
            if (divider == -1 || (divider == date_time.length() - 1)) {
                throw new DukeException("Need to have end time.");
            }
            String end = date_time.substring(divider + 1, date_time.length());
            event.start = LocalDateTime.parse(date_time.substring(0, divider), getStart);
            event.endTime = LocalTime.parse(end, DateTimeFormatter.ofPattern("HHmm"));
            event.display = event.start.format(getStart) + "-" + event.endTime.format(getTime);
            return event;
        } catch (DateTimeParseException e){
            throw new DukeException("Incorrect Event date/time format." + System.lineSeparator() +
                    "    Please key in date(d/MM/yyyy) start_time(HHmm)-end_time(HHmm)");
        }
    }

    /**
     * Called during construction of Event object.
     * Reads substring from text file and gets date, start time and end time of task.
     * @param date_time Must be in the form (d/M/yyyy HHmm-HHmm)
     * @return DateTime object as dateTime parameter of Event object.
     * @throws DukeException thrown if missing end time. This should not happen unless text file was tempered with.
     */
    public static DateTime readEventTime(String date_time) throws DukeException{
        DateTime event = new DateTime();
        int divider = date_time.indexOf("-");
        if (divider == -1 || (divider == date_time.length() -1)
            || date_time.substring(divider + 1).replace(" ", "").equals("")){
            throw new DukeException("Need to have end time.");
        }
        String end = date_time.substring(divider + 1, date_time.length());
        event.start = LocalDateTime.parse(date_time.substring(0, divider), getStart);
        event.endTime = LocalTime.parse(end, DateTimeFormatter.ofPattern("HHmm"));
        event.display = event.start.format(getStart) + "-" + event.endTime.format(getTime);
        return event;
    }

    /**
     * Originally used to add suffix to date of month
     * @param dd day of month as a 2 digit number
     * @return 1 or 2 digit number with appropriate suffix.
     */
    private static String getDayOfMonth(String dd) {
        if (dd.substring(0,1).equals("0")){
            dd = dd.substring(1,dd.length());
        }
        int day = Integer.parseInt(dd);
        if (day >= 11 && day <= 13) {
            return day + "th";
        }
        switch (day % 10) {
            case 1:  return day + "st";
            case 2:  return day + "nd";
            case 3:  return day + "rd";
            default: return day + "th";
        }
    }

    public String toString(){
        return display;
    }
}
