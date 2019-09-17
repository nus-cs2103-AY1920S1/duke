/**
 * Represents the Help command, which is responsible for showing all possible commands.
 */
public class HelpCommand extends Command {
    /**
     * Constructs the Help Command Object.
     */
    public HelpCommand(String input) {
        super.input = input;
    }

    /**
     * Determines whether or should the Duke App should terminate.
     *
     * @return returns false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to display all possible commands.
     *
     * @param tasks   The TaskList of the current Duke App
     * @param storage The Storage unit being used by the Duke app.
     */
    public String execute(TaskList tasks, Storage storage) {
        //do something
        String output = "List of available commands\n";
        output += "    - help || shows the list of avaibale commands\n";
        output += "    - list || Displays the tasks currently stored\n";
        output += "    - todo <task name> || adds a todo task to the list\n";
        output += "    - event <task name> /at <time with format dd/mm/yyyy hh:mm> || adds an event task to the list\n";
        output += "    - deadline <task name> /by <time with format dd/mm/yyyy hh:mm> "
                + "|| adds an deadline task to the list\n";
        output += "    - done <task number> || completes the indicated task on the list\n";
        output += "    - delete <task number> || deletes the indeicated task from the list\n";
        output += "    - bye || closes the app\n";
        return output;
    }
}
