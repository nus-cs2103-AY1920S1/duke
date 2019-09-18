package command;

/**
 *<h1> ErrorCommand</h1>
 * The ErrorCommand class
 * 1) Instructs the Textformatter to return an error message for the user
 *
 */

public class ErrorCommand extends Command {
    Exception err;

    /**
     * Constructor for ErrorCommand Object
     * Stores new error as <param>err</param>
     *
     * @param x new error
     */

    public ErrorCommand(Exception x) {
        err = x;
    }

    /**
     * Returns the error as a formatted String
     *
     * @return String the formatted output, after running through formatOutput()
     */


    public String executeCommand() {
        return this.formatOutput();
    }

    /**
     * Returns the formatted command as a formatted string
     *
     *@return String formatted
     */

    public String formatOutput() {
        return TextFormatter.errorFormat(err);
    }


}
