package datetime;

import exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.function.Supplier;

/**
 * Wrapper of LocalDateTime. Stores date and time for Task.
 */
public class DateTime  implements Cloneable {
    private LocalDateTime start;
    private LocalTime endTime;
    private String display;
    private static DateTimeFormatter getStart = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static DateTimeFormatter getTime = DateTimeFormatter.ofPattern("HHmm");

    /**
     * Called during construction of Deadline object.
     * Reads substring from user input and gets date and time of task.
     * @param dateTime Must be in the form (d/M/yyyy HHmm)
     * @return DateTime object as dateTime parameter of Deadline object.
     * @throws DukeException thrown if date_time is not presented in the correct form.
     */
    public static DateTime setDeadline(String dateTime) throws DukeException {
        try {
            DateTime deadline = new DateTime();
            deadline.start = LocalDateTime.parse(dateTime, getStart);
            deadline.display = deadline.start.format(getStart);
            return deadline;
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect Deadline date/time format." + System.lineSeparator()
                    + "    Please key in date(d/M/yyyy) time(HHmm)");
        }
    }

    /**
     * Called during construction of Deadline object.
     * Reads substring from text file and gets date and time of the task
     * @param dateTime Must be in the form (d/M/yyyy HHmm).
     * @return DateTime object as dateTime parameter of Deadline object.
     */
    public static DateTime readDeadLine(String dateTime) {
        DateTime deadline = new DateTime();
        deadline.start = LocalDateTime.parse(dateTime, getStart);
        deadline.display = deadline.start.format(getStart);
        return deadline;
    }

    /**
     * Called during construction of Event object.
     * Reads substring from user input and gets date, start time and end time of task.
     * @param dateTime Must be in the form (d/MM/yyyy HHmm-HHmm)
     * @return DateTime object as dateTime parameter of Event object.
     * @throws DukeException thrown if missing end time or if date_time is not presented in the correct form.
     */
    public static DateTime setEventTime(String dateTime) throws DukeException {
        try {
            DateTime event = new DateTime();
            int divider = dateTime.indexOf("-");
            if (divider == -1 || (divider == dateTime.length() - 1)) {
                throw new DukeException("Need to have end time.");
            }
            String end = dateTime.substring(divider + 1, dateTime.length());
            event.start = LocalDateTime.parse(dateTime.substring(0, divider), getStart);
            event.endTime = LocalTime.parse(end, DateTimeFormatter.ofPattern("HHmm"));
            event.display = (event.start.format(getStart) + "-" + event.endTime.format(getTime));
            return event;
        } catch (DateTimeParseException e) {
            throw new DukeException("Incorrect Event date/time format." + System.lineSeparator()
                    + "    Please key in date(d/M/yyyy) start_time(HHmm)-end_time(HHmm)");
        }
    }

    /**
     * Called during construction of Event object.
     * Reads substring from text file and gets date, start time and end time of task.
     * @param dateTime Must be in the form (d/M/yyyy HHmm-HHmm)
     * @return DateTime object as dateTime parameter of Event object.
     * @throws DukeException thrown if missing end time. This should not happen unless text file was tempered with.
     */
    public static DateTime readEventTime(String dateTime) throws DukeException {
        DateTime event = new DateTime();
        int divider = dateTime.indexOf("-");
        if (divider == -1 || (divider == dateTime.length() - 1)
            || dateTime.substring(divider + 1).replace(" ", "").equals("")) {
            throw new DukeException("Need to have end time.");
        }
        String end = dateTime.substring(divider + 1, dateTime.length());
        event.start = LocalDateTime.parse(dateTime.substring(0, divider), getStart);
        event.endTime = LocalTime.parse(end, DateTimeFormatter.ofPattern("HHmm"));
        event.display = event.start.format(getStart) + "-" + event.endTime.format(getTime);
        return event;
    }

    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }



    /**
     * Set date for next recurring event.
     * @param unitTime Time period in days/ weeks/ months/ years
     * @param quantity Amount of said time period.
     * @return new Date
     * @throws DukeException when DateTime cannot be cloned or when there is invalid unitTime input.
     */
    public DateTime setNewDate(String unitTime, int quantity) throws DukeException {
        try{
            DateTime newTime = (DateTime) this.clone();
            switch (unitTime) {
            case "day": case "days":
                newTime.start = newTime.start.plusDays(quantity);
                newTime.updateDisplay();
                return newTime;
            case "week": case "weeks":
                newTime.start = newTime.start.plusDays(quantity * 7);
                newTime.updateDisplay();
                return newTime;
            case "month": case "months":
                newTime.start = newTime.start.plusMonths(quantity);
                newTime.updateDisplay();
                return newTime;
            case "year": case"years":
                newTime.start = newTime.start.plusYears(quantity);
                newTime.updateDisplay();
                return newTime;
            default:
                throw new DukeException("Event can only be set to recur every n days/ weeks/ months/ years, not " + unitTime);
            }
        } catch (CloneNotSupportedException e){
            throw new DukeException("Error cloning DateTime");
        }
    }

    private void updateDisplay(){
        if (this.endTime == null){
            this.display = this.start.format(getStart);
        } else {
            this.display = this.start.format(getStart) + "-" + this.endTime.format(getTime);
        }
    }

    public String toString() {
        return display;
    }
}
