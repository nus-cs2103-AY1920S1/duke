/**
 * ListCommand extends Command.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String title = " Here are the tasks in your list:\n";
        int tasksSize = tasks.size();
        if (tasksSize == 0) {
            return Ui.frontSpace + " You have no tasks in your list.\n";
        } else {
            String temp = "";
            for (int i = 0; i < tasksSize; i++) {
                if ((i + 1) < 10) {
                    temp += Ui.frontSpace + " " + (i + 1) + ". " + tasks.getTaskList().get(i) + "\n";
                } else {
                    temp += Ui.frontSpace + " " + (i + 1) + "." + tasks.getTaskList().get(i) + "\n";
                }
            }
            return String.format(
                    Ui.frontSpace + " You have no tasks in your list.\n",
                    temp + "\n");
        }
    }
}