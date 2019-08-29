package duke.task;

import duke.exception.DukeException;

import static duke.parser.DateTimeParser.inputDateFormatter;
import static duke.parser.DateTimeParser.inputTimeFormatter;
import static duke.parser.DateTimeParser.outputDateFormatter;
import static duke.parser.DateTimeParser.outputTimeFormatter;
import static duke.parser.DateTimeParser.parseDate;
import static duke.parser.DateTimeParser.parseTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class Deadline extends Task {
    private LocalDate byDate;
    private Optional<LocalTime> byTime;

    /**
     * Represents a task with a deadline.
     *
     * @param description The description of the task
     * @param by          The deadline containing date or/and time (e.g. 01/01/2019 1800 means 1st January 2019, 6pm).
     *                    The date input must follow dd/mm/yyyy format.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
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
     * Converts a deadline into serialized form. (e.g.
     * D | 0 | return book | 11/10/2019 1100).
     */
    @Override
    public String serialize() {
        return String.format("D | %d | %s | %s%s",
                getStatusCode(),
                description,
                inputDateFormatter.format(byDate),
                byTime.isPresent()
                        ? " " + inputTimeFormatter.format(byTime.get())
                        : "");
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), formatBy());
    }
}