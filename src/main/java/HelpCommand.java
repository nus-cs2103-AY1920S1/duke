/**
 * Represents the Help command, which is responsible for showing all possible commands
 */
public class HelpCommand extends Command {
    /**
     * Constructs the Help Command Object.
     */
    public HelpCommand() {

    }

    /**
     * Determines whether or should the Duke App should terminate.
     * @return returns false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to display all possible commands.
     * @param tasks The TaskList of the current Duke App
     * @param ui The Ui being used by the Duke App
     * @param storage The Storage unit being used by the Duke app.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        //do something
        ui.showMessage("List of available commands");
        ui.showMessage(6, "- help || shows the list of avaibale commands");
        ui.showMessage(6, "- list || Displays the tasks currently stored");
        ui.showMessage(6, "- todo <task name> || adds a todo task to the list");
        ui.showMessage(6, "- event <task name> /at <time with format dd/mm/yyyy hh:mm>");
        ui.showMessage(10, "adds an event task to the list");
        ui.showMessage(6, "- deadline <task name> /by <time with format dd/mm/yyyy hh:mm>");
        ui.showMessage(10, "adds an deadline task to the list");
        ui.showMessage(6, "- done <task number> || completes the indicated task on the list");
        ui.showMessage(6, "- delete <task number> || deletes the indeicated task from the list");
        ui.showMessage(6, "- bye || closes the app");
    }
}
