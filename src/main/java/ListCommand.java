import java.util.ArrayList;

/**
 * Represents a list command which is responsible for displaying the current list of tasks.
 */
public class ListCommand extends Command {
    /**
     * Constructs a ListCommand Object.
     */
    public ListCommand(String input) {
        super.input = input;
    }

    /**
     * Determines whether or should the Duke App should terminate.
     *
     * @return returns false.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to display all tasks currently saved.
     *
     * @param tasks   The TaskList of the current Duke App.
     * @param storage The Storage unit being used by the Duke app.
     */
    public String execute(TaskList tasks, Storage storage) {
        String output = "Here are the tasks in your list:\n";
        ArrayList<Task> allTask = tasks.getList();
        for (int i = 0; i < allTask.size(); i++) {
            output += "    " + (i + 1) + "." + allTask.get(i).toString() + "\n";
        }
        return output;
    }
}
