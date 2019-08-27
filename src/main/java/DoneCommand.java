import java.io.IOException;

public class DoneCommand extends Command {
	protected String command;
	
	public DoneCommand(String command) {
		this.command = command;
	}
	
	public void execute(TaskList tasks, Storage storage) throws IOException {
		tasks.completeTask(command);
		storage.updateComplete(command);
	}
	
	public boolean isExit() {
		return false;
	}
}
