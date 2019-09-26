package tasks;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    String by;
    LocalDateTime date1;

    /**
     * This is a constructor for Deadline.
     *
     * @param description description of task
     * @param by          due date of deadline
     */
    public Deadline(String description, String by) {
        super(description);
        assert by != null;
        this.by = by;
        super.symbol = "D";
        getDate();
    }

    /**
     * Parses the date string in task and creates a LocalDateTime object
     */

    public void getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        if (!by.contains(" ")) {
            LocalDate d1 = LocalDate.parse(by, formatter);
            date1 = d1.atTime(23, 59);
        } else {
            String[] dateArray = by.split(" ");
            LocalDate d1 = LocalDate.parse(dateArray[0], formatter);
            date1 = d1.atTime(Integer.parseInt(dateArray[1]) / 100, Integer.parseInt(dateArray[1]) % 100);
        }
    }

    @Override
    public LocalDateTime getDateTime() {
        return date1;
    }

    @Override
    public String getExtraInfo() {
        return by;
    }

    @Override
    public void postpone(int daysToPostpone, int hoursToPostpone, int minutesToPostpone) {
        date1 = date1.plus(Duration.ofDays(daysToPostpone))
                .plus(Duration.ofHours(hoursToPostpone)).plus(Duration.ofMinutes(minutesToPostpone));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +  date1 + ")" + super.getNotes();
    }
}