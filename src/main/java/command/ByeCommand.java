package command;


/**The ByeCommand class.
 * 1) Instructs the textFormatter to return the String for saying Bye
 * 2) Returns the bye string so that the UI can print it out
 */

public class ByeCommand extends Command {

    /**
     * Instructs textFormatter to return the bye String.
     *
     *@return bye String
     */

    public String formatOutput() {
        return TextFormatter.byeFormat();
    }

    /**
     * Returns the formatted Bye String.
     *
     * @return String the formatted output, after running through formatOutput()
     */


    public String executeCommand() {
        return this.formatOutput();
    }

}

