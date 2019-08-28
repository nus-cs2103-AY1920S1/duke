package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate byDate;
    private LocalTime byTime;

    /**
     * Constructs a <code>Deadline</code> Object to represent a task with a deadline.
     *
     * @param description The description of the deadline item
     * @param by The deadline containing date and time
     */
    public Deadline(String description, String by) {
        super(description);
        String[] dateTimeArr = by.split(" ");
        this.byDate = parseDate(dateTimeArr[0]);
        this.byTime = parseTime(dateTimeArr[1]);
    }

    private LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    private LocalTime parseTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return LocalTime.parse(timeString, formatter);
    }

    private String formatBy() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE, d MMMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");
        return String.format("%s, %s", dateFormatter.format(byDate), timeFormatter.format(byTime));
    }

    @Override
    public String serialize() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        return String.format("D | %d | %s | %s %s", getStatusCode(), description,
                dateFormatter.format(byDate), timeFormatter.format(byTime));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatBy() + ")";
    }
}