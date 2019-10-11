package command;

/**The HelloCommand class.
 * 1) Instructs the Textformatter to return a Hello message for the user
 *
 */

public class HelloCommand extends Command {

    /**
     * Returns the hello message as a formatted String.
     *
     * @return String the formatted output, after running through formatOutput()
     */

    public String executeCommand() {
        return this.formatOutput();
    }


    /**
     * Returns the formatted command as a formatted string.
     *
     *@return String formatted
     */

    public String formatOutput() {
        return TextFormatter.helloFormat();
    }

}


