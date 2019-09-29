/**
 * Command to exit program.
 */
public class ExitCommand extends Command {

    /**
     * Execute the exit command.
     *
     * @param listOfTasks the list of tasks
     * @param ui          The ui which displays the output
     * @param storage     to store the task
     */
    @Override
    public void execute(TaskList listOfTasks, Ui ui, Storage storage) {
        ui.println("Bye. Hope to see you again soon!");
    }

    /**
     * Method to know if it is an exit command.
     *
     * @return A boolean which states if it is an exit command
     */
    public boolean isExit() {
        return true;
    }
}
