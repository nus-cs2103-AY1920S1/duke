/**
 * Command to view the task list.
 */
public class ListCommand extends Command {

    /**
     * Execute the list command.
     *
     * @param listOfTasks the list of tasks
     * @param ui          The ui which displays the output
     * @param storage     to store the task
     * @throws DukeException if error
     */
    @Override
    public void execute(TaskList listOfTasks, Ui ui, Storage storage) throws DukeException {
        if (listOfTasks.getSize() <= 0) {
            throw new DukeException("No tasks in the list");
        }
        int count = 1;
        for (Task t : listOfTasks.getListOfTasks()) {
            ui.println(count + ". " + t);
            count++;
        }
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
