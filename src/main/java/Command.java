import java.text.ParseException;

abstract class Command {
	protected String command;
	protected String remainingCommand;
	protected Task task;

	public Command(String command, String remainingCommand) {
		this.command = command;
		this.remainingCommand = remainingCommand;
	}

	public boolean isExit() {
		return this.command.equals("bye");
	}

	abstract public void execute(TaskList tasks, Ui ui) throws DukeException, ParseException;

	public String toString() {
		return "Command type: " + command + ", description: " + remainingCommand;
	}
}