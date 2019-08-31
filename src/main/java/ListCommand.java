import java.util.ArrayList;

/**
 * Represents a list command which is responsible for displaying the current list of tasks.
 */
public class ListCommand extends Command{
    /**
     * Constructs a ListCommand Object.
     */
    public ListCommand() {

    }

    /**
     * Determines whether or should the Duke App should terminate.
     * @return returns false.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to display all tasks currently saved.
     * @param tasks The TaskList of the current Duke App
     * @param ui The Ui being used by the Duke App
     * @param storage The Storage unit being used by the Duke app.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks in your list:");
        ArrayList<Task> allTask = tasks.getList();
        for (int i = 0; i < allTask.size(); i++) {
            ui.showMessage(6, (i + 1) + "." + allTask.get(i).toString());
        }
    }
}
