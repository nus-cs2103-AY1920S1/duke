package com.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.duke.exception.DukeIllegalArgumentException;

/**
 * Encapsulates an Event task.
 */
public class Event extends Task {

    private LocalDate date;

    private static final DateTimeFormatter FORMAT_USER_INPUT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter FORMAT_FILE_DATE_STRING = DateTimeFormatter.ofPattern("dd MMMM yyyy");
    private static final String INVALID_INPUT_ERROR_MESSAGE = "Invalid input for event.\n"
            + "You may key in one of the following:\n"
            + "event <task> /at [dd/MM/yyyy]\n"
            + "event <task> /at [dd/MM/yyyy HHmm]\n"
            + "event <task> /at [dd/MM/yyyy HHmm HHmm]";

    /**
     * Constructs an Event object.
     *
     * @param description Task description.
     * @param date        Event date represented by String.
     */
    Event(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date, FORMAT_USER_INPUT_DATE);
    }

    Event(String description, String date, boolean isFromFile) {
        super(description);
        if (isFromFile) {
            this.date = LocalDate.parse(date, FORMAT_FILE_DATE_STRING);
        } else {
            throw new DukeIllegalArgumentException("The input string is not from file");
        }
    }

    /**
     * Generates an Event task from input String.
     *
     * @param s String representation of event.
     * @return Event task.
     */
    public static Event genEvent(String s) {
        String[] newInputArray = s.split("/at");
        String des = "";
        String at = "";
        try {
            des = newInputArray[0].trim();
            at = newInputArray[1].trim();
            String[] dateTimeArray = at.split(" ");

            if (dateTimeArray.length == 1) {
                return new Event(des, at);
            }

            if (dateTimeArray.length == 2) {
                return new EventWithStartTime(des, dateTimeArray[0], dateTimeArray[1]);
            } else if (dateTimeArray.length == 3) {
                return new EventWithEndTime(des, dateTimeArray[0], dateTimeArray[1], dateTimeArray[2]);
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

    static Event genEventFromFile(String des, String at) {
        String[] dateTimeArray = at.split(" ");
        switch (dateTimeArray.length) {
        case 3:
            return new Event(des, at, true);
        case 4:
            return new EventWithStartTime(des, dateTimeArray[0] + " " + dateTimeArray[1] + " "
                    + dateTimeArray[2], dateTimeArray[3], true);
        case 6:
            return new EventWithEndTime(des, dateTimeArray[0] + " " + dateTimeArray[1] + " "
                    + dateTimeArray[2], dateTimeArray[3], dateTimeArray[5], true);
        default:
            throw new DukeIllegalArgumentException("Invalid input for event from file");
        }
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " at: " + date.format(FORMAT_FILE_DATE_STRING);
    }
}
