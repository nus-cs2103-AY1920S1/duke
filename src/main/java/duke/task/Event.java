package duke.task;

import duke.exception.DukeException;

import static duke.parser.DateTimeParser.inputDateFormatter;
import static duke.parser.DateTimeParser.inputTimeFormatter;
import static duke.parser.DateTimeParser.outputDateFormatterShort;
import static duke.parser.DateTimeParser.outputTimeFormatter;
import static duke.parser.DateTimeParser.parseDate;
import static duke.parser.DateTimeParser.parseTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;

    /**
     * Represents a task with both start time and end time.
     *
     * @param description The description of the event
     * @param at          Date and time of both start and end of the event. If the dates of the start and
     *                    end time of the event are the same, only the start date is required. (e.g. '11/10/2019
     *                    1000 1100' means the event starts on 11th October 2019, 10AM and ends at 11AM on the
     *                    same day.) However, if the start and end dates of the event are event, the end date
     *                    should be specified. (e.g. '01/01/2019 0700 03/01/2019 1800' means the event starts
     *                    on 1st January 2019, 6PM and ends on 3rd January 2019, 6PM. )
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
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid time format.");
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
     * Converts an event into serialized form. (e.g.
     * E | 0 | coding workshop | 11/10/2019 1000 11/10/2019 1100).
     */
    @Override
    public String serialize() {
        return String.format("E | %d | %s | %s %s %s %s",
                getStatusCode(),
                description,
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