package duke.task;

import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import static duke.parser.DateTimeParser.inputDateFormatter;
import static duke.parser.DateTimeParser.inputTimeFormatter;
import static duke.parser.DateTimeParser.outputDateFormatter;
import static duke.parser.DateTimeParser.outputTimeFormatter;
import static duke.parser.DateTimeParser.parseDate;
import static duke.parser.DateTimeParser.parseTime;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private String by;
    private LocalDate byDate;
    private Optional<LocalTime> byTime;

    /**
     * Constructs a Deadline object given a description and the deadline (with date)
     * of the task as a String.
     *
     * @param description description of the task
     * @param by          deadline which contains a date and a optional time (e.g.
     *                    '01/01/2019 1800' means 1st January 2019, 6pm). The time
     *                    can be omitted. The date input must follow dd/mm/yyyy format
     *                    and the time must follow HHmm format if it exists.
     * @throws DukeException if invalid time format is passed in
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;
        String[] dateTimeArr = by.split(" ", 2);
        try {
            this.byDate = parseDate(dateTimeArr[0]);
            if (dateTimeArr.length == 1) {
                this.byTime = Optional.empty();
            } else {
                this.byTime = Optional.of(parseTime(dateTimeArr[1]));
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid time format.");
        }
    }

    @Override
    public Task copy() {
        try {
            Task copy = new Deadline(getDescription(), by);
            if (this.getIsDone()) {
                copy.markAsDone();
            }
            return copy;
        } catch (DukeException e) {
            return null;
        }
    }

    private String formatBy() {
        if (byTime.isPresent()) {
            return String.format("%s, %s",
                    outputDateFormatter.format(byDate),
                    outputTimeFormatter.format(byTime.get()));
        } else {
            return String.format("%s", outputDateFormatter.format(byDate));
        }
    }

    /**
     * Converts a deadline into encoded form (e.g.
     * 'D | 0 | return book | 11/10/2019 1100').
     */
    @Override
    public String encode() {
        return String.format("D | %d | %s | %s%s",
                getStatusCode(),
                getDescription(),
                inputDateFormatter.format(byDate),
                byTime.map(byTime -> " " + inputTimeFormatter.format(byTime)).orElse(""));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), formatBy());
    }
}