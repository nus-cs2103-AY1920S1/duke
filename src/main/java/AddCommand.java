import java.io.IOException;

public class AddCommand extends Command {
	protected String command;
	
	public AddCommand(String command) {
		this.command = command;
	}
	
	public void execute(TaskList tasks, Storage storage) throws EmptyDescriptionException, InvalidInputException,
			InvalidDescriptionException,  IOException {
		try {
			if (command.startsWith("todo")) {
				System.out.println("in");
				tasks.addTodo(command);
				storage.storeTodo(command);
			} else if (command.startsWith("deadline")) {
				tasks.addDeadline(command);
				storage.storeDeadline(command);
			} else if (command.startsWith("event")) {
				tasks.addEvent(command);
				storage.storeEvent(command);
			} else {
				throw new InvalidInputException();
			}
		} catch (Exception e) {
			handleException(e);
		}
	}
	
	public boolean isExit() {
		return false;
	}
	
	@Override
	public String toString() {
		return "AddCommand " + this.command;
	}
}
