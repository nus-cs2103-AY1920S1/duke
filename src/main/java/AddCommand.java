import java.io.IOException;

/**
 * Command to add tasks.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for AddCommand.
     *
     * @param task A task to be added to the task list
     */
    public AddCommand(Task task) {
        assert task != null;
        this.task = task;
    }

    /**
     * Execute the add command.
     *
     * @param listOfTasks the list of tasks to be added to
     * @param ui          The ui which displays the output
     * @param storage     to store the task
     */
    @Override
    public void execute(TaskList listOfTasks, Ui ui, Storage storage) throws IOException {
        listOfTasks.addTask(task);
        ui.println("Got it. I've added this task:");
        ui.println(task);
        ui.println("Now you have " + listOfTasks.getSize() + " tasks in the list.");
        storage.writeToFile(listOfTasks.getListOfTasks());
    }

    /**
     * Method to know if it is an exit command.
     *
     * @return A boolean which states if it is an exit command
     */
    public boolean isExit() {
        return false;
    }
}
