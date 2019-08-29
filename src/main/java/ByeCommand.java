public class ByeCommand implements Command {
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Prints termination statement.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Bye. Hope to see you again soon!");
    }
}
