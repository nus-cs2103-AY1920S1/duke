/**
 * Represent user command to display their task list.
 */

public class ShowCommand extends Command {

    private TaskList tasks;

    /**
     * Represents an action to display their tasks.
     * @param command Type of task
     */
    public ShowCommand(String command) {
        super(command);
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        this.tasks = tasks;
    }

    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        StringBuilder printStr = new StringBuilder();
        printStr.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            printStr.append((i + 1) + ". " + tasks.getTask(i) + "\n");
        }

        return printStr.toString();
    }
}
