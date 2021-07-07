package seedu.duke.logic.validation;

import seedu.duke.commons.exceptions.commandexceptions.EmptyCommandException;
import seedu.duke.commons.exceptions.commandexceptions.InvalidTaskDescriptionException;
import seedu.duke.commons.exceptions.dateandtimeexceptions.InvalidDateException;
import seedu.duke.commons.exceptions.dateandtimeexceptions.InvalidTimeException;
import seedu.duke.commons.exceptions.listexceptions.InvalidListIndexException;
import seedu.duke.commons.exceptions.listexceptions.InvalidListRangeException;
import seedu.duke.commons.exceptions.listexceptions.InvalidNumberFormatException;
import seedu.duke.model.dateandtime.Date;
import seedu.duke.model.dateandtime.Time;
import seedu.duke.model.task.TaskList;

import java.util.ArrayList;

/**
 * Defines a Validation class containing static methods.
 * Validates strings parsed for general purposes
 * Includes validation for commands, dates, times and lists.
 */
public class Validation {

    /**
     * Returns a String array of two elements containing task description, date, start and end time respectively.
     * @param descriptionAndDateTimeString String in the form [task description] [split token] [date and time]
     * @param splitToken String representing the token that is meant to split the given line of
     *                   descriptionAndDateTimeString into description and date/ time.
     * @return String array of two elements containing task description, date and time respectively.
     * @throws InvalidTaskDescriptionException If the format of the String entered is invalid.
     */

    public static String[] getValidatedDescriptionAndDateTime(String descriptionAndDateTimeString, String splitToken)
            throws InvalidTaskDescriptionException {
        assert !splitToken.equals("") : "A split between description and date/time was requested "
                + "but the split token to be used was empty.";
        if (!descriptionAndDateTimeString.contains(splitToken)) {
            throw new InvalidTaskDescriptionException("Please separate deadline description and date/time by \""
                    + splitToken + "\". \n"
                    + "Note that date/time of an event cannot be empty.");
        }

        String[] arr = descriptionAndDateTimeString.split(splitToken);

        if (arr.length < 2) {
            throw new InvalidTaskDescriptionException("The description of an event cannot be empty.");
        }
        assert arr.length == 2 : "Invalid length of description and date/ time String "
                + "but exception was not thrown.";
        return new String[]{arr[0], arr[1]};
    }

    /**
     * Ensures a command not meant to trailing strings following it does not have any.
     * Trailing spaces will be automatically removed by the program.
     * @param command String representation of a single-word command.
     * @param taskString Description of a task, following a single-word command.
     * @throws InvalidTaskDescriptionException If taskString taken in is non-empty.
     */
    public static void ensureEmptyTaskString(String command, String taskString) throws InvalidTaskDescriptionException {
        if (!taskString.equals("")) {
            throw new InvalidTaskDescriptionException("There cannot be any additional characters after the \""
                    + command + "\" command"
                    + " (other than trailing spaces).");
        }
        assert taskString.equals("") : "Exception meant to be thrown for non-empty task description "
                + "but an exception was not thrown";
    }

    /**
     * Ensures a command or task requiring trailing string(s) following it has it / them.
     * @param task Task or single-word command that requires a following description.
     * @param taskString Description of a task, following a single-word command.
     * @throws InvalidTaskDescriptionException If taskString taken in is empty.
     */
    public static void ensureNonEmptyTaskString(String task, String taskString) throws InvalidTaskDescriptionException {
        if (taskString.equals("")) {
            if (task.equals("delete") || task.equals("done") || task.equals("random")) {
                throw new InvalidTaskDescriptionException("The index for a " + task + " command cannot be empty.");
            } else if (task.charAt(0) == 'a' || task.charAt(0) == 'e' || task.charAt(0) == 'i'
                    || task.charAt(0) == 'o' || task.charAt(0) == 'u') {
                throw new InvalidTaskDescriptionException("The description of an " + task + " cannot be empty.");
            } else {
                throw new InvalidTaskDescriptionException("The description of a " + task + " cannot be empty.");
            }
        }
        assert !taskString.equals("") : "Exception meant to be thrown for empty task description "
                + "but an exception was not thrown";
    }

    /**
     * Ensures that a user does not enter an empty line of command.
     * @param command String representation of a single-word command to the program.
     * @throws EmptyCommandException If user inputs an empty line of command.
     */
    public static void ensureNonEmptyCommand(String command) throws EmptyCommandException {
        if (command.equals("")) {
            throw new EmptyCommandException("Command cannot be empty.");
        }
        assert !command.equals("") : "Exception meant to be thrown for empty command "
                + "but an exception was not thrown";
    }

    /**
     * Returns a validated natural number from a String.
     * @param taskString String to be converted to a positive non-zero integer.
     * @param canBeEmpty Denotes whether the String taken in is allowed to be empty, in which case a value of
     *                   1 will be returned.
     * @return A validated natural number converted from the given String.
     * @throws InvalidNumberFormatException If the numeric representation of the given String is not a natural number.
     */
    public static int getValidatedNaturalNumber(String taskString, boolean canBeEmpty)
            throws InvalidNumberFormatException {
        if (taskString.equals("")) {
            if (!canBeEmpty) {
                throw new InvalidNumberFormatException("Please enter only a valid natural number after the intended " 
                        + "command (separated by a space).");
            } else {
                return 1;
            }
        }

        int n = 0;

        try {
            n = Integer.parseInt(taskString);
            if (n <= 0) {
                throw new NumberFormatException();
            } else {
                return n;
            }
        } catch (NumberFormatException ex) {
            throw new InvalidNumberFormatException("Please enter only a valid natural number after the intended "
                    + " command (separated by a space).");
        }
    }

    /**
     * Returns a validated Date object.
     * Ensures that input String is in valid DD/MM/YYYY format.
     * Ensures there are at most 12 months in a year.
     * Ensures the day of the month in the year entered is valid.
     * @param date String representation of a date in DD/MM/YYYY format.
     * @return Validated Date object containing the day, month, year of the date parameter.
     * @throws InvalidDateException If the date entered is invalid.
     */
    public static Date getValidatedDate(String date) throws InvalidDateException {
        if (date.length() != 10) {
            throw new InvalidDateException("Please ensure date format is in DD/MM/YYYY");
        }

        String[] dateArr = date.split("/");
        if (dateArr.length != 3) {
            throw new InvalidDateException("Please ensure date format is in DD/MM/YYYY");
        }

        int day = 0;
        int month = 0;
        int year = 0;

        try {
            day = Integer.parseInt(dateArr[0]);
            month = Integer.parseInt(dateArr[1]);
            year = Integer.parseInt(dateArr[2]);
            if (day < 1) {
                throw new InvalidDateException("Calendar day must start from 1");
            } else if (month < 1) {
                throw new InvalidDateException("Calendar month must start from 1");
            } else if (year < 0) {
                throw new InvalidDateException("Calendar year must start from 0");
            } else if (month > 12) {
                throw new InvalidDateException("There cannot be more than 12 months in a year.");
            }
        } catch (NumberFormatException ex) {
            throw new InvalidDateException("Please ensure date format is in DD/MM/YYYY");
        }

        if ((month <= 7 && month % 2 != 0) || (month > 7 && month % 2 == 0)) {
            if (day > 31) {
                throw new InvalidDateException("There cannot be more than 31 days in " + Date.getMonth(month));
            }
        } else if (month != 2) {
            if (day > 30) {
                throw new InvalidDateException("There cannot be more than 30 days in " + Date.getMonth(month));
            }
        } else {
            if (Date.isLeapYear(year) && day > 29) {
                throw new InvalidDateException("There cannot be more than 29 days in " + Date.getMonth(month) + " "
                        + year + ".");
            } else if (!Date.isLeapYear(year) && day > 28) {
                throw new InvalidDateException("There cannot be more than 28 days in " + Date.getMonth(month) + " "
                        + year + ".");
            }
        }

        assert day >= 1 && day <= 31 : "Day in Date object to be created is out of range "
                + "but no exception was thrown.";
        assert month >= 1 && month <= 12 : "Month in Date object to be created is out of range "
                + "but no exception was thrown.";
        assert year >= 0 : "Year in Date object to be created is out of range "
                + "but no exception was thrown.";

        return new Date(day, month, year);
    }

    /**
     * Returns a validated Time object.
     * Ensures that input String is in valid HHMM format.
     * Ensures hours is not more than 23 and minutes is not more than 59.
     * @param time String representation of a time in HHMM format.
     * @return Validated Time object containing the day, month, year of the time parameter.
     * @throws InvalidTimeException If the time entered is invalid.
     */
    public static Time getValidatedTime(String time) throws InvalidTimeException {
        if (time.length() != 4) {
            throw new InvalidTimeException("Please ensure time format is in HHMM format (24 hours).");
        }

        int hour = 0;
        int minute = 0;

        try {
            hour = Integer.parseInt(time.substring(0, 2));
            minute = Integer.parseInt(time.substring(2, 4));
            if (hour < 0 || minute < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            throw new InvalidTimeException("Please ensure time format is in HHMM format (24 hours).");
        }

        if (hour > 23) {
            throw new InvalidTimeException("Please check that hours is not more than 23.");
        } else if (minute > 59) {
            throw new InvalidTimeException("Please check that minutes is not more than 59.");
        }

        assert hour >= 0 && hour < 24 : "Hour of Time object to be created is out of range "
                + "but no exception was thrown.";
        assert hour >= 0 && hour <= 59 : "Minute of Time object to be created is out of range "
                + "but no exception was thrown.";

        return new Time(hour, minute);
    }

    /**
     * Returns an ArrayList of ranges represented by ArrayLists of size 2 denoting start and end indexes of a TaskList.
     * @param tasks TaskList of which the validated range is to be returned.
     * @param rangeList String representation of a range in the format of ranges separated by commas.
     *                  eg. "3-4, 5-6, 9, 10-11".
     * @return An ArrayList of ranges represented by ArrayLists of size 2 denoting start and end indexes of a TaskList.
     * @throws InvalidListIndexException If the TaskList range given is invalid. eg. "3-5-1"
     */
    public static ArrayList<ArrayList<Integer>> getValidatedListRange(TaskList tasks, String rangeList)
            throws InvalidListIndexException {
        String[] ranges = rangeList.split(",");
        ArrayList<ArrayList<Integer>> outputRange = new ArrayList<>();

        for (String range : ranges) {
            String[] rangeArray = range.trim().split("-");
            ArrayList<Integer> intRange = new ArrayList<>(2);

            if (rangeArray.length > 2) {
                throw new InvalidListRangeException("Please ensure ranges of numbers are separated by "
                        + "commas, and each range contains at most one dash.");
            } else if (rangeArray.length == 1) {
                int index = getValidatedListIndex(tasks, rangeArray[0]);
                for (int i = 0; i < 2; i++) {
                    intRange.add(index);
                }
            } else {
                int startIndex = getValidatedListIndex(tasks, rangeArray[0]);
                int endIndex = getValidatedListIndex(tasks,rangeArray[1]);
                if (startIndex > endIndex) {
                    throw new InvalidListRangeException("The starting number of a list range cannot be more than "
                            + "the ending number.");
                }
                assert startIndex <= endIndex : "Starting index of the list is more than the ending index, "
                        + "but no exception was thrown.";
                intRange.add(startIndex);
                intRange.add(endIndex);
            }
            assert intRange.size() == 2 : "ArrayList containing range of numbers has more or less than the "
                    + "required number of two elements (representing start and end index.";

            outputRange.add(intRange);
        }

        return outputRange;
    }

    /**
     * Returns a validated index for the given list.
     * Takes in a string and ensures that it is a valid positive integer within the range of the given TaskList 
     * (numbered from 1 to n in a TaskList of size n).
     * Ensures that any n that is returned will be able to access the TaskList without encountering a general
     * number format exception that will cause the program to terminate in the event that the index is invalid or
     * out of bounds.
     * Post-condition: A client of this method may use the returned value to access tasks in the TaskList 
     * if the String representing the index of the item in the taskList is valid.
     * @param tasks TaskList of which the validated index is to be returned.
     * @param k String that represents the ith element of the TaskList (k ranges from 1 to n).
     * @return An integer value i that can be used to access the item in the taskList (i ranges from 0 to n - 1).
     * @throws InvalidListIndexException If k is less than 0, more than the size of the list or
     *                                   cannot be parsed as an integer.
     */
    public static int getValidatedListIndex(TaskList tasks, String k) throws InvalidListIndexException {
        if (k.equals("")) {
            throw new InvalidListIndexException("Please enter a valid numerical value after the intended command"
                    + " (separated by a space).\n" + "The number cannot be empty for this command.");
        }

        int n = 0;

        try {
            n = Integer.parseInt(k);
            if (n <= 0 || n > tasks.size()) {
                throw new NumberFormatException();
            }
            try {
                tasks.get(n - 1);
            } catch (Exception e) {
                assert false : "Duke exception was to be thrown to handle invalid list index but "
                        + "was not thrown. Instead, " + e.getClass().getCanonicalName() + " was thrown.";
            }
            return n - 1;
        } catch (NumberFormatException ex) {
            if (tasks.size() == 1) {
                throw new InvalidListIndexException("Please enter a valid numerical value from 1 to the taskList's size"
                        + " after the intended command (separated by a space).\n"
                        + "There is currently 1 task in the list.\n"
                        + "Try entering \"list\" to view the full list of tasks.");
            } else {
                throw new InvalidListIndexException("Please enter a valid numerical value from 1 to the taskList's size"
                        + " after the intended command (separated by a space).\n"
                        + "There are currently " + tasks.size() + " tasks in the list.\n"
                        + "Try entering \"list\" to view the full list of tasks.");
            }
        }

    }
}
