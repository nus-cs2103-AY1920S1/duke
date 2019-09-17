public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    /**
     * Executes the command.
     * Lists all files in the task.
     * @param tasks to handle the tasks
     * @param ui to get user input
     * @param storage to store tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printListMessage(tasks);
    }

    /**
     * Boolean to exit from program
     * @return true or false depending if we want to stop the program
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
