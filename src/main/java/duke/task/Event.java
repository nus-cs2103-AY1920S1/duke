package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;

    /**
     * Constructs an <code>Event</code> Object to represent an event.
     *
     * @param description The description of the event item
     * @param at The duration of the event (including date and time of both start and end)
     */
    public Event(String description, String at) {
        super(description);
        String[] dateTimeArr = at.split(" ");
        this.startDate = parseDate(dateTimeArr[0]);
        this.startTime = parseTime(dateTimeArr[1]);
        if (dateTimeArr.length == 3) {
            this.endDate = parseDate(dateTimeArr[0]);
            this.endTime = parseTime(dateTimeArr[2]);
        } else {
            this.endDate = parseDate(dateTimeArr[2]);
            this.endTime = parseTime(dateTimeArr[3]);
        }
    }

    private LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    private LocalTime parseTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return LocalTime.parse(timeString, formatter);
    }

    private String formatAt() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mma");

        if (startDate.equals(endDate)) {
            return String.format("%s, %s - %s", dateFormatter.format(startDate),
                    timeFormatter.format(startTime), timeFormatter.format(endTime));
        } else {
            return String.format("%s, %s - %s, %s", dateFormatter.format(startDate),
                    timeFormatter.format(startTime), dateFormatter.format(endDate),
                    timeFormatter.format(endTime));
        }
    }

    @Override
    public String serialize() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        return String.format("E | %d | %s | %s %s %s %s", getStatusCode(), description,
                dateFormatter.format(startDate), timeFormatter.format(startTime),
                dateFormatter.format(endDate), timeFormatter.format(endTime));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatAt() + ")";
    }
}