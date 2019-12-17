package com.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.duke.exception.DukeIllegalArgumentException;

/**
 * Encapsulates deadline task.
 */
public class Deadline extends Task {

    private LocalDate date;
    private static final DateTimeFormatter FORMAT_USER_INPUT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMAT_FILE_DATE_STRING = DateTimeFormatter.ofPattern("dd MMMM yyyy");
    private static final String INVALID_INPUT_ERROR_MESSAGE = "Invalid input for deadline.\n"
            + "You may key in one of the following with valid values:\n"
            + "deadline <task> /by [dd/MM/yyyy]\n"
            + "deadline <task> /by [dd/MM/yyyy HHmm]";

    /**
     * Constructs a Deadline object.
     *
     * @param description Task description.
     * @param date        Deadline date.
     */
    Deadline(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date, FORMAT_USER_INPUT_DATE);
    }

    Deadline(String description, String date, boolean isFromFile) {
        super(description);
        if (isFromFile) {
            this.date = LocalDate.parse(date, FORMAT_FILE_DATE_STRING);
        } else {
            throw new DukeIllegalArgumentException("The input string is not from file");
        }

    }

    /**
     * Generates a Deadline task from input String.
     *
     * @param s Deadline date and time represented by String.
     * @return Deadline task.
     */
    public static Deadline genDeadline(String s) {
        String[] newInputArray = s.split("/by");
        String des = "";
        String by = "";
        try {
            des = newInputArray[0].trim();
            by = newInputArray[1].trim();
            String[] dateTimeArray = by.split(" ");

            if (dateTimeArray.length == 1) {
                return new Deadline(des, by);
            } else if (dateTimeArray.length == 2) {
                return new DeadlineWithTime(des, dateTimeArray[0], dateTimeArray[1]);
            } else {
                throw new DukeIllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeIllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
        } catch (DateTimeParseException e) {
            if (e.getMessage().contains("Invalid value")) {
                throw new DukeIllegalArgumentException(e.getMessage());
            } else {
                throw new DukeIllegalArgumentException(INVALID_INPUT_ERROR_MESSAGE);
            }
        }
    }

    static Deadline genDeadlineFromFile(String description, String by) {
        String[] dateTimeArray = by.split(" ");
        switch (dateTimeArray.length) {
        case 3:
            return new Deadline(description, by, true);
        case 4:
            return new DeadlineWithTime(description, dateTimeArray[0] + " " + dateTimeArray[1] + " "
                    + dateTimeArray[2], dateTimeArray[3], true);
        default:
            throw new DukeIllegalArgumentException("Invalid input for deadline from file");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " + date.format(FORMAT_FILE_DATE_STRING);
    }
}
