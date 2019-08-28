/**
 * ListCommand extends Command.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String title = " Here are the tasks in your list:\n";
        int tasksSize = tasks.size();
        if (tasksSize == 0) {
            System.out.println(" You have no tasks in your list.");
        } else {
            for (int i = 0; i < tasksSize; i++) {
                if ((i + 1) < 10) {
                    System.out.println(Ui.frontSpace + " " + (i + 1) + ". " + tasks.getTaskList().get(i));
                } else {
                    System.out.println(Ui.frontSpace + " " + (i + 1) + "." + tasks.getTaskList().get(i));
                }
            }
        }
    }
}