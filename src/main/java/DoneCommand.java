import java.io.IOException;

/**
 * Command to set tasks as done.
 */
public class DoneCommand extends Command {
    private int index;

    /**
     * Constructor for DoneCommand.
     *
     * @param index of the task in the task list
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Execute the delete command.
     *
     * @param listOfTasks the list of tasks to be added to
     * @param ui          The ui which displays the output
     * @param storage     to store the task
     * @throws DukeException if error
     */
    @Override
    public void execute(TaskList listOfTasks, Ui ui, Storage storage) throws DukeException, IOException {
        if (index < 0 || index > listOfTasks.getSize() - 1) {
            throw new DukeException("Invalid task number");
        }
        listOfTasks.getListOfTasks().get(index).setDone();
        storage.writeToFile(listOfTasks.getListOfTasks());
        ui.println("Nice! I've marked this task as done:");
        ui.println(listOfTasks.getListOfTasks().get(index));
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
