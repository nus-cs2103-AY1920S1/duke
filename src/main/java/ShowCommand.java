/**
 * Represent user command to display their task list.
 */

public class ShowCommand extends Command {

    /**
     * Represents an action to display their tasks.
     * @param command Type of task
     */
    public ShowCommand(String command) {
        super(command);
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showList(tasks);
    }

    public boolean isExit() {
        return false;
    }
}
