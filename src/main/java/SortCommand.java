/**
 * Handles sorting of tasks, earliest deadline first
 */
public class SortCommand extends Command {
    public SortCommand(String command, String commandDetails, String indent) {
        super(command, commandDetails, indent);
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printResponse("I have sorted your list.\n " + "Here are the tasks in your list:\n"
                + tasks.returnSortedTasksString());
    }
}
