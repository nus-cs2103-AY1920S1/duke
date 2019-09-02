import java.util.ArrayList;

class ExitCommand extends Command {
	public ExitCommand(String command, String remainingCommand) {
		super(command, remainingCommand);
	}

	public void execute(TaskList tasks, Ui ui) throws DukeException {
		ui.exit();
	}
}