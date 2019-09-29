import java.io.IOException;

/**
 * Command to delete a task.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructor for DeleteCommand.
     *
     * @param index of the task in the task list
     */
    public DeleteCommand(int index) {
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
        if (index < 0 || index >= listOfTasks.getSize()) {
            throw new DukeException("Invalid index");
        }
        Task temp = listOfTasks.removeTask(index);
        ui.println("Noted. I've removed this task:");
        ui.println(temp);
        ui.println("Now you have " + listOfTasks.getSize() + " tasks in the list.");
        storage.writeToFile(listOfTasks.getListOfTasks());
    }

    /**
     * Method to know if it is an exit command.
     *
     * @return A boolean which states if it is an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
