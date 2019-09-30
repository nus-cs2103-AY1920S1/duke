package exception;

/**
 * Represents an Exception Handler that can display to user the respective error messages when required.
 * Used by Duke to handle exceptions thrown in the Main program.
 */
public class ExceptionHandler {
    
    /**
     * Displays an error that indicates to the user that an incorrect format for Date/Time has been given.
     * @return a String that displays the error message.
     */
    public String showParseDateTimeError() {
        String errorMessage = "Unable to parse Date & Time of task!\n"
                + "Please follow the following format for Date & Time:\n\n"
                + "- Date: 'dd/mm/yy' e.g. 10/02/19 (10 Feb 2019)\n"
                + "- Time: 'hhmm' (24-hr format) e.g. 0730 (07:30AM)";

        return errorMessage;
    }

    /**
     * Displays an error that indicates to the user that a valid DukeCommand input has been given, 
     * but Duke has trouble evaluating it due to abnormal input somewhere in the command given.
     * @param e The IncorrectDukeCommand to display the error message of.
     * @return a String that displays the error message.
     */
    public String showDukeCommandEvaluationError(IncorrectDukeCommand e) {
        String errorMessage = "Looks like the above Duke Command has failed. That's because...\n" + e.getMessage();

        return errorMessage;
    }

    /**
     * Displays an error that indicates to the user that an invalid DukeCommand has been given.
     * @return a String that displays the error message.
     */
    public String showUnknownDukeCommandError() {
        String errorMessage = "I'm sorry, but I don't know what that means...";

        return errorMessage;
    }

    /**
     * Displays an error that indicates to the user that no input was given when prompted for one.
     * @return a String that displays the error message.
     */
    public String showVoidDukeCommandError() {
        String errorMessage =  "I can't do anything if you don't tell me what to do...";

        return errorMessage;
    }

    /**
     * Displays an error that indicates to the user that an error occurred with file saving/loading.
     * This error should not appear unless the user created a directory with an identical name and path
     * as the file that is saved to keep track of user's tasks when the program exits.
     * @return a String that displays the error message.
     */
    public String showDukeIoError() {
        String errorMessage =  "File could not be read/saved.";

        return errorMessage;
    }

    /**
     * Displays a general DukeException that is not caught by any of the above to the user.
     * @return a String that displays the error message.
     */
    public String showDukeError(DukeException e) {
        return e.getMessage();
    }
}
