public class ErrorCommand extends Command {
    ErrorCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showGenericError();
    }
}
