package duke;

public class ErrorCommand extends Command {
    ErrorCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DukeException {
        ui.showGenericError();
    }
}
