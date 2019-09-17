import java.util.ArrayList;

public class FindCommand extends Command {
    public FindCommand(String input) {
        super.input = input;
    }

    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command to find the specified word in all tasks.
     *
     * @param tasks   The TaskList of the current Duke App
     * @param storage The Storage unit being used by the Duke app.
     */
    public String execute(TaskList tasks, Storage storage) {
        String filter = input.substring(4).trim();
        assert filter != null || !filter.equals("") : "filter should not be null";
        if (filter.length() == 0) {
            return "No keyword to search";
        }
        String output = "Here are the tasks in your list:\n";
        ArrayList<Task> allTask = tasks.getList();
        for (int i = 0; i < allTask.size(); i++) {
            if (allTask.get(i).toString().contains(filter)) {
                output += "    " + (i + 1) + "." + allTask.get(i).toString() + "\n";
            }
        }
        return output;
    }
}
