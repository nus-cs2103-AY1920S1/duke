import java.io.IOException;

public class DeleteCommand extends Command {
	protected String command;
	
	public DeleteCommand(String command) {
		this.command = command;
	}
	
	public void execute(TaskList tasks, Storage storage) throws IOException {
		tasks.deleteTask(command);
		storage.updateDelete(command);
	}
	
	public boolean isExit() {
		return false;
	}
}
