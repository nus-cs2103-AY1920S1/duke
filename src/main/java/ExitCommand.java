public class ExitCommand extends Command {
    protected String command;

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showBye();
    }

    public boolean isExit() {
        return true;
    }
}