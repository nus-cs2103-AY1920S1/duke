public class ListCommand implements Command {
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * List down all the task in the list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showMessage("Here are the task in your list:");
        for (int i = 1; i <= tasks.size(); ++i) {
            ui.showMessage(i + "." + tasks.get(i));
        }
    }
}
