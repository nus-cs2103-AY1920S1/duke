/**
 * Represents a help command.
 *
 * @author Michelle Yong
 */
public class HelpCommand extends Command {
    /**
     * Creates a help command.
     */
    public HelpCommand() {}

    /**
     * Executes the help command and shows the list of commands possible.
     *
     * @param storage The storage for the file with all the tasks.
     * @param taskList The taskList used.
     * @param ui The User Interface used.
     * @return The list of commands available.
     */
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        StringBuilder help = new StringBuilder("List of commands:\n");
        help.append("- todo TASK_NAME\n");
        help.append("- deadline TASK_NAME /by DATE\n");
        help.append("- event TASK_NAME /at DATE\n");
        help.append("- list\n");
        help.append("- done TASK_NUMBER\n");
        help.append("- find KEYWORD\n");
        help.append("- priority TASK_NUMBER LEVEL\n");
        help.append("- clear\n");
        help.append("Note:\nDATE is in the format \"dd MMM yyyy hh:mm\".\n");
        help.append("Words in UPPER_CASE are the parameters to be supplied.");
        return help.toString();
    }
}
