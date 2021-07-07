package seedu.duke.logic.validation;

import seedu.duke.commons.exceptions.loadexceptions.InvalidDoneStatusException;
import seedu.duke.commons.exceptions.loadexceptions.InvalidTaskTypeException;
import seedu.duke.commons.exceptions.loadexceptions.InvalidTokenNumberException;

/**
 * Defines a LoadValidation class containing static methods.
 * Validates strings parsed from the file pointed to by the file path of a Storage object.
 * Inherits methods from Validation class.
 */
public class LoadValidation extends Validation {

    /**
     * Returns a validated Type of Task represented by a single-character String.
     * @param type Type of task represented by a single-character String.
     * @return Validated Type of Task based on given type of Task.
     * @throws InvalidTaskTypeException If input type of Task is invalid or does not correspond to any
     *                                  known Type of Task.
     */
    public static String getValidatedTaskType(String type) throws InvalidTaskTypeException {
        if (!(type.equals("T") || type.equals("D") || type.equals("E"))) {
            throw new InvalidTaskTypeException("Incorrect Type in Load File");
        } else {
            assert type.equals("T") || type.equals("D") || type.equals("E")
                    : "Exception meant to be thrown for wrong Task type "
                    + "but exception was not thrown.";
            return type;
        }
    }

    /**
     * Returns either 1 or 0, representing whether a Task has been marked as done (completed).
     * @param doneStatus String of either "0" or "1", with the latter representing that a Task has been marked as done.
     * @return An integer 1 or 0, representing the state of whether a Task has been marked as done in the storage file.
     * @throws InvalidDoneStatusException If doneStatus is anything other than the String "0" or "1".
     */
    public static int getValidatedDoneStatus(String doneStatus) throws InvalidDoneStatusException {
        try {
            int res = Integer.parseInt(doneStatus);
            if (!(res == 0 || res == 1)) {
                throw new NumberFormatException();
            }
            assert res == 0 || res == 1 : "Exception meant to be thrown for wrong done status "
                    + "but exception was not thrown.";
            return res;
        } catch (NumberFormatException ex) {
            throw new InvalidDoneStatusException("Invalid Done Status in Load File");
        }
    }

    /**
     * Ensures that there are valid number of tokens in a line of text in the Storage file representing a Task.
     * @param type String representation of Task type in the Storage file, denoted by a single letter.
     * @param arr An array containing words from a line of text separated by spaces, with elements
     *            representing tokens.
     * @throws InvalidTokenNumberException If the number of tokens fails to correspond to that of it's Task type
     *                                     in the line of text.
     */
    public static void ensureValidNumberOfTokens(String type, String[] arr) throws InvalidTokenNumberException {
        if ((type.equals("D") || type.equals("E")) && arr.length != 4) {
            throw new InvalidTokenNumberException("Incorrect Number of Tokens in Load File");
        } else if (type.equals("T") && arr.length != 3) {
            throw new InvalidTokenNumberException("Incorrect Number of Tokens in Load File");
        }
        assert arr.length == 3 || arr.length == 4 : "Exception meant to be thrown for invalid number of tokens "
                + "but exception was not thrown.";
    }
}
