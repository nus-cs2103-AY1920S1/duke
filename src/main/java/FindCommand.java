
/**
 * Command to find tasks by keyword.
 */
public class FindCommand extends Command {
    private String find;

    /**
     * Constructor for DeleteCommand.
     *
     * @param find representing a string containing the keyword
     */
    public FindCommand(String find) {
        this.find = find;
    }

    /**
     * Execute the find command.
     *
     * @param listOfTasks the list of tasks
     * @param ui          The ui which displays the output
     * @param storage     the place that stores the tasks
     * @throws DukeException if error
     */
    @Override
    public void execute(TaskList listOfTasks, Ui ui, Storage storage) throws DukeException {
        ui.showLine();
        ui.println(" Here are the matching tasks in your list:");
        int count = 0;
        for (Task t : listOfTasks.getListOfTasks()) {
            if (t.getDescription().contains(find)) {
                ui.println(t);
                count++;
            }
        }
        if (count == 0) {
            throw new DukeException("No matching tasks found");
        }
        ui.showLine();
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
