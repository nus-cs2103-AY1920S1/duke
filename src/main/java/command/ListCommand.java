package command;

/**
 *<h1> ListCommand</h1>
 * The ListCommand class
 * 1) Instructs the relevant TaskList to return all its tasks
 * 2) Instructs the Textformatter to return a message for the user
 *
 */

public class ListCommand extends Command {

    /**
     * Returns the formatted command as a formatted string
     *
     *@return String formatted
     */
    public String formatOutput() {
        return TextFormatter.listFormat(reference.getList());
    }

}
