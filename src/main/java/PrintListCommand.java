/**
 * A Command to print the task list.
 */
public class PrintListCommand extends Command {

    /**
     * Executes the command to print the task list.
     * @param tasks The task list.
     * @param ui The ui that handles user output.
     * @param storage The storage that handles saving and loading the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            ui.print(String.format("%d. %s", i, tasks.get(i - 1)));
        }
    }

    /**
     * Returns a boolean value signalling whether the program should exit.
     * @return A boolean value indicating whether the program should exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
