package duke.command;

/**
 * Class representing a command that lists out the contents
 * of the current task list. Inherits from the Command abstract
 * class.
 * @see Command
 */
public class ListCommand extends Command {

    public static final String KEYWORD = "list";

    /**
     * Prints out a string representation of TaskList object to the
     * screen for the user to view.
     *
     * @return result feedback of the command to be printed to the user
     */
    public String execute() {
        if (taskList.getSize() == 0) {
            return "Nice! You have no pending tasks in the current list!";
        } else {
            return "Here are the tasks in your current list:\n"
                    + ui.indentMessage(taskList.toString());
        }
    }
}
