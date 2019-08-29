package exception;

import misc.Ui;

/**
 * Represents an Exception Handler that can display to user the respective error messages when required.
 * Used by Duke to handle exceptions thrown in the Main program.
 */
public class ExceptionHandler {
    
    /**
     * Displays an error that indicates to the user that an incorrect format for Date/Time has been given.
     */
    public void showParseDateTimeError() {
        String errorMessage = Ui.spaces(5) + "Unable to parse Date & Time of task!\n"
                + Ui.spaces(5) + "Please follow the following format for Date & Time:\n\n"
                + Ui.spaces(7) + "- Date: 'dd/mm/yy' e.g. 10/02/19 (10 Feb 2019)\n"
                + Ui.spaces(7) + "- Time: 'hhmm' (24-hr format) e.g. 0730 (07:30AM)";

        System.out.println(errorMessage);
    }

    /**
     * Displays an error that indicates to the user that a valid DukeCommand input has been given, 
     * but Duke has trouble evaluating it due to abnormal input somewhere in the command given.
     * @param e The IncorrectDukeCommand to display the error message of.
     */
    public void showDukeCommandEvaluationError(IncorrectDukeCommand e) {
        String errorMessage = String.format("%sLooks like the above Duke Command has failed. That's because...\n\n%s",
                Ui.spaces(5), Ui.spaces(5) + e.getMessage());

        System.out.println(errorMessage);
    }

    /**
     * Displays an error that indicates to the user that an invalid DukeCommand has been given.
     */
    public void showUnknownDukeCommandError() {
        String errorMessage = Ui.spaces(5) + "I'm sorry, but I don't know what that means... \u2639";

        System.out.println(errorMessage);
    }

    /**
     * Displays an error that indicates to the user that no input was given when prompted for one.
     */
    public void showVoidDukeCommandError() {
        String errorMessage = Ui.spaces(5) + "I can't do anything if you don't tell me what to do... \ud83d\ude2d";

        System.out.println(errorMessage);
    }

    /**
     * Displays an error that indicates to the user that an error occurred with file saving/loading.
     * This error should not appear unless the user created a directory with an identical name and path
     * as the file that is saved to keep track of user's tasks when the program exits.
     */
    public void showDukeIoError() {
        String errorMessage = Ui.spaces(5) + "File could not be read/saved.";

        System.out.println(errorMessage);
    }

    /**
     * Displays a general DukeException that is not caught by any of the above to the user.
     */
    public void showDukeError(DukeException e) {
        System.out.println(e.getMessage());
    }
}
