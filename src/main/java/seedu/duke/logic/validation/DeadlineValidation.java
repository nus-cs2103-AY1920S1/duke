package seedu.duke.logic.validation;

import seedu.duke.commons.exceptions.commandexceptions.InvalidTaskDescriptionException;
import seedu.duke.commons.exceptions.dateandtimeexceptions.InvalidDateAndTimeException;
import seedu.duke.commons.exceptions.dateandtimeexceptions.InvalidTimeException;
import seedu.duke.model.dateandtime.Date;
import seedu.duke.model.dateandtime.DateAndTime;
import seedu.duke.model.dateandtime.Time;

/**
 * Defines a DeadlineValidation class containing static methods.
 * Validates Strings parsed to create a Deadline.
 * Inherits methods from Validation class.
 */
public class DeadlineValidation extends Validation {

    /**
     * Returns a String array of two elements containing task description and date / time respectively.
     * @param descriptionAndDateTimeString String in the form [task description] [" /by "] [date and optional time].
     * @return String array of two elements containing task description and date / time respectively.
     * @throws InvalidTaskDescriptionException If the format of the String entered is invalid.
     */
    public static String[] getValidatedDescriptionAndDateTime(String descriptionAndDateTimeString)
            throws InvalidTaskDescriptionException {
        return Validation.getValidatedDescriptionAndDateTime(descriptionAndDateTimeString, " /by ");
    }

    /**
     * Returns a String array of two elements containing date and time respectively.
     * @param dateAndTimeString String in the form of either [date] or [date] [time].
     * @return String array of two elements containing task description and date / time respectively.
     * @throws InvalidDateAndTimeException If the format of the String entered is invalid.
     */
    public static DateAndTime getValidatedDateAndTime(String dateAndTimeString) throws InvalidDateAndTimeException {
        String[] dateTimeArr = dateAndTimeString.split(" ");
        if (dateTimeArr.length > 2) {
            throw new InvalidDateAndTimeException("Please ensure date format is either in DD/MM/YYYY format or "
                    + "DD/MM/YYYY HHMM format (24 hours).");
        }
        Date date = getValidatedDate(dateTimeArr[0]);
        if (dateTimeArr.length > 1 && dateTimeArr[1].length() > 4) {
            throw new InvalidTimeException("Please ensure time format is in HHMM format (24 hours). "
                    + "Note that there can only be one single argument for time in a Deadline type of Task.");
        }
        Time time = dateTimeArr.length > 1 ? getValidatedTime(dateTimeArr[1]) : null;

        assert date != null : "Validated date and time returned for an "
                + "event contain null DateAndTime objects.";

        if (time != null) {
            return new DateAndTime(date, time);
        } else {
            return new DateAndTime(date);
        }
    }
}
