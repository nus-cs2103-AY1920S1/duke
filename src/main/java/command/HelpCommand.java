package command;


/**The HelpCommand class.
 * 1) Tells the user what to do
 */

public class HelpCommand extends Command {

    /**
     * Instructs textFormatter to return the Help String.
     *
     *@return Help String
     */

    public String formatOutput() {
        return TextFormatter.helpFormat();
    }

    /**
     * Returns the formatted Help String.
     *
     * @return String the formatted output, after running through formatOutput()
     */


    public String executeCommand() {
        return this.formatOutput();
    }

}
