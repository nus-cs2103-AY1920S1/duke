import java.io.IOException;

public class ByeCommand extends Command {
	protected String command;
	
	public ByeCommand(String command) {
		this.command = command;
	}
	
	public void execute(TaskList tasks, Storage storage) throws IOException {
		System.out.println("BYE! See you again next time!");
	}
	
	public boolean isExit() {
		return true;
	}
}
