public class TagCommand extends Command {

    TagCommand() {
        super();
    }

    /**
     * Executes the command.
     * Tags the specific task
     * @param tasks to handle the tasks
     * @param ui to get user input
     * @param storage to store tasks
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "";
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
