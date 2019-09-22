package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static duke.parser.DateTimeParser.inputDateFormatter;
import static duke.parser.DateTimeParser.inputTimeFormatter;
import static duke.parser.DateTimeParser.outputDateFormatterShort;
import static duke.parser.DateTimeParser.outputTimeFormatter;
import static duke.parser.DateTimeParser.parseDate;
import static duke.parser.DateTimeParser.parseTime;

/**
 * Represents a task with both start time and end time.
 */
public class Event extends Task {
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;

    /**
     * Constructs an Event object given a description and the start and end time (with date)
     * of the event as a String.
     *
     * @param description the description of the event
     * @param at          a String containing date and time of both start and end of the event.
     *                    If the dates of the start and end time of the event are the same,
     *                    only the start date is required (e.g. '11/10/2019 1000 1100' means the event
     *                    starts on 11th October 2019, 10AM and ends at 11AM on the same day).
     *                    However, if the start and end dates of the event are event, the end date
     *                    should be specified (e.g. '01/01/2019 0700 03/01/2019 1800' means
     *                    the event starts on 1st January 2019, 6PM and ends on 3rd January 2019, 6PM).
     * @throws DukeException if start time is before end time or invalid time format is passed in
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        String[] dateTimeArr = at.split(" ");
        try {
            this.startDate = parseDate(dateTimeArr[0]);
            this.startTime = parseTime(dateTimeArr[1]);
            if (dateTimeArr.length == 3) {
                this.endDate = parseDate(dateTimeArr[0]);
                this.endTime = parseTime(dateTimeArr[2]);
            } else {
                this.endDate = parseDate(dateTimeArr[2]);
                this.endTime = parseTime(dateTimeArr[3]);
            }
            this.validate();
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid time format.");
        }
    }

    /**
     * Alternative constructor for Event object.
     *
     * @param description the description of the event
     * @param startDate   the start date of the event
     * @param startTime   the start time of the event
     * @param endDate     the end date of the event
     * @param endTime     the end time of the event
     * @throws DukeException if start time of the event is before the end time
     */
    public Event(String description, LocalDate startDate, LocalTime startTime, LocalDate endDate,
                 LocalTime endTime) throws DukeException {
        super(description);
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.validate();
    }

    public Event(String description, LocalDate eventDate, LocalTime startTime,
                 LocalTime endTime) throws DukeException {
        this(description, eventDate, startTime, eventDate, endTime);
    }

    @Override
    public Task copy() {
        try {
            Task copy = new Event(this.getDescription(), this.startDate, this.startTime, this.endDate, this.endTime);
            if (this.getIsDone()) {
                copy.markAsDone();
            }
            return copy;
        } catch (DukeException e) {
            return null;
        }
    }

    private void validate() throws DukeException {
        if (startDate.isAfter(endDate)) {
            throw new DukeException("Start date cannot be after end date");
        } else if (startDate.equals(endDate) && startTime.isAfter(endTime)) {
            throw new DukeException("Start time cannot be after end time");
        }
    }

    private String formatAt() {
        if (startDate.equals(endDate)) {
            return String.format("%s, %s - %s",
                    outputDateFormatterShort.format(startDate),
                    outputTimeFormatter.format(startTime),
                    outputTimeFormatter.format(endTime));
        } else {
            return String.format("%s, %s - %s, %s",
                    outputDateFormatterShort.format(startDate),
                    outputTimeFormatter.format(startTime),
                    outputDateFormatterShort.format(endDate),
                    outputTimeFormatter.format(endTime));
        }
    }

    /**
     * Converts an event into encoded form (e.g.
     * 'E | 0 | coding workshop | 11/10/2019 1000 11/10/2019 1100').
     */
    @Override
    public String encode() {
        return String.format("E | %d | %s | %s %s %s %s",
                getStatusCode(),
                getDescription(),
                inputDateFormatter.format(startDate),
                inputTimeFormatter.format(startTime),
                inputDateFormatter.format(endDate),
                inputTimeFormatter.format(endTime));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), formatAt());
    }
}