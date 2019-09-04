package helper;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateTimeRangeHelper {
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date;
    private String displayText;

    /**
     * Creates a datetimerangehelper data transfer object.
     * @param start time.
     * @param end time.
     * @param date date.
     * @param text description.
     */
    public DateTimeRangeHelper(LocalTime start, LocalTime end, LocalDate date, String text) {
        startTime = start;
        endTime = end;
        this.date = date;
        displayText = text;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getText() {
        return displayText;
    }
}
