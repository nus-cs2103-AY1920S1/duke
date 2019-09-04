import java.util.ArrayList;

public class FindCommand extends Command {
    public FindCommand() {
    }

    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to find the specified word in all tasks.
     * @param tasks The TaskList of the current Duke App
     * @param ui The Ui being used by the Duke App
     * @param storage The Storage unit being used by the Duke app.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String filter = ui.getLastCommand().substring(5).trim();
        ui.showMessage("Here are the tasks in your list:");
        ArrayList<Task> allTask = tasks.getList();
        for (int i = 0; i < allTask.size(); i++) {
            if (allTask.get(i).toString().contains(filter)) {
                ui.showMessage(6, (i + 1) + "." + allTask.get(i).toString());
            }
        }
    }
}
