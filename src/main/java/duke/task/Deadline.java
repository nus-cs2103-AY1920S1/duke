package duke.task;

import java.time.LocalDateTime;

/**
 * Class for deadline tasks.
 */
public class Deadline extends Task {
    /**
     * Variable for date and time.
     */
    private LocalDateTime dateTime;

    /**
     * Constructor for event.
     *
     * @param description description of event
     * @param date        date and time of event
     */
    public Deadline(String description, String date) {
        super(description);
        String[] dateSplit = date.split("\\s");
        String[] d = dateSplit[0].split("/");
        int day = Integer.parseInt(d[0]);
        int month = Integer.parseInt(d[1]);
        int year = Integer.parseInt(d[2]);
        int hour = Integer.parseInt(dateSplit[1].substring(0, 2));
        int min = Integer.parseInt(dateSplit[1].substring(2));
        dateTime = LocalDateTime.of(year, month, day, hour, min);
    }

    /**
     * Returns date and time as a String.
     *
     * @return String of date and time
     */
    private String getDate() {
        return dateTime.getDayOfMonth() + "/" + dateTime.getMonthValue() + "/" + dateTime.getYear() +
                " " + String.format("%02d", dateTime.getHour()) + String.format("%02d", dateTime.getMinute());
    }

    /**
     * Returns String in text file format.
     *
     * @return a String to write on text file
     */
    public String toFile() {
        return "D|" + getStatusIcon() + "|" + description + "|" + getDate();
    }

    /**
     * Returns String to output on terminal.
     *
     * @return a String to output on terminal
     */
    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + "(by: " + getDate() + ")";
    }
}
