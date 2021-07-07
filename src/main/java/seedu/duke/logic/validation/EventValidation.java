package seedu.duke.logic.validation;

import seedu.duke.commons.exceptions.commandexceptions.InvalidTaskDescriptionException;
import seedu.duke.commons.exceptions.dateandtimeexceptions.InvalidDateAndTimeException;
import seedu.duke.commons.exceptions.dateandtimeexceptions.InvalidTimeException;
import seedu.duke.model.dateandtime.Date;
import seedu.duke.model.dateandtime.DateAndTime;
import seedu.duke.model.dateandtime.Time;

/**
 * Defines an EventValidation class containing static methods.
 * Validates strings parsed to create an Event.
 * Inherits methods from Validation class.
 */
public class EventValidation extends Validation {

    /**
     * Returns a String array of two elements containing task description, date, start and end time respectively.
     * @param descriptionAndDateTimeString String in the form [task description] [" /at "] [start time] ["-"]
     *                                     ["end time].
     * @return String array of two elements containing task description, date, start and end time respectively.
     * @throws InvalidTaskDescriptionException If the format of the String entered is invalid.
     */

    public static String[] getValidatedDescriptionAndDateTime(String descriptionAndDateTimeString)
            throws InvalidTaskDescriptionException {
        return Validation.getValidatedDescriptionAndDateTime(descriptionAndDateTimeString, " /at ");
    }

    /**
     * Returns a String array of three elements containing date, start time and end time respectively.
     * @param dateAndTimeString String in the form of [date] [start time] [end time].
     * @return String array of three elements containing date, start time and end time respectively.
     * @throws InvalidDateAndTimeException If the format of the String entered is invalid.
     */
    public static DateAndTime getValidatedDateAndTime(String dateAndTimeString) throws InvalidDateAndTimeException {
        String[] dateTimeArr = dateAndTimeString.split(" ");
        if (dateTimeArr.length != 2) {
            throw new InvalidDateAndTimeException("Please ensure date format is in DD/MM/YYYY HHMM-HHMM "
                    + "format (24 hours).");
        }
        Date date = getValidatedDate(dateTimeArr[0]);
        String[] timeArr = dateTimeArr[1].split("-");
        if (timeArr.length != 2) {
            throw new InvalidTimeException("Please ensure time format is in HHMM-HHMM format (24 hours).");
        }
        Time timeStart = getValidatedTime(timeArr[0]);
        Time timeEnd = getValidatedTime(timeArr[1]);

        assert date != null && timeStart != null && timeEnd != null : "Validated date and time returned for an "
                + "event contain null DateAndTime objects.";

        return new DateAndTime(date, timeStart, timeEnd);
    }
}
