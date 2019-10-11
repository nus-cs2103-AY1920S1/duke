/**
 * Represents a command that searches for a task with a given keyword.
 */

import java.util.ArrayList;

public class FindCommand extends Command {
    private String description;

    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        int count = 1;
        String str = toString();
        for (Task task : tasks.getList()) {
            if (task.getDescription().contains(description)) {
                matchingTasks.add(task);
            }
        }
        for (Task task : matchingTasks) {
            str += count + "." + task + "\n";
            count++;
        }

        return str;
    }

    @Override
    public String toString() {
        return "Here are the matching tasks in your list:\n";
    }
}
