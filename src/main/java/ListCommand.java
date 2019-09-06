/**
 * Represents a command to print out the tasks in the arraylist.
 */

public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        int listCount = 1;
        String str = toString();
        for (Task task : tasks.getList()) {
            str += listCount + "." + task + "\n";
            listCount++;
        }

        return str;
    }

    @Override
    public String toString() {
        return "Here are the tasks in your list:\n";
    }
}
