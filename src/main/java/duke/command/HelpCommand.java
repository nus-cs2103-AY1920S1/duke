package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {
	private boolean isCommandSpecific;
	private String inputCommand;
	
	public HelpCommand(boolean isCommandSpecific, String inputCommand) {
		super("Help", new Task());
		this.isCommandSpecific = isCommandSpecific;
		this.inputCommand = inputCommand;
	}
	
	
	@Override
	public String execute(TaskList list, Ui ui, Storage storage) {
		String helpOutput = null;
		if (isCommandSpecific) {
			helpOutput = executeSpecificCommand(inputCommand);
		} else {
			helpOutput = executeGeneral();
		}
		return helpOutput;
	}
	
	private String executeSpecificCommand(String commandInterested) {
		String helpLineStart = "Here is what I found!\n";
		
		boolean isByeCommand = commandInterested.toLowerCase().equals("bye");
		boolean isFindCommand = commandInterested.toLowerCase().equals("find");
		boolean isListCommand = commandInterested.toLowerCase().equals("list");
		boolean isDeleteCommand = commandInterested.toLowerCase().equals("delete");
		boolean isToDoCommand = commandInterested.toLowerCase().equals("todo");
		boolean isDeadlineCommand = commandInterested.toLowerCase().equals("deadline");
		boolean isEventCommand = commandInterested.toLowerCase().equals("event");
		boolean isDoneCommand = commandInterested.toLowerCase().equals("done");
		
		String helpLine = "Sorry! Command not found!\n";
		if (isToDoCommand) {
			helpLine = commandInterested + " <task description>\n";
		} else if (isDeadlineCommand) {
			helpLine = commandInterested + " <task description> /by dd/mm/yyyy\n";
		} else if (isEventCommand) {
			helpLine = commandInterested + " <task description> /at dd/mm/yyyy\n";
		} else if (isDeleteCommand) {
			helpLine = commandInterested + " <task number>";
		} else if (isListCommand) {
			helpLine = commandInterested;
		} else if (isFindCommand) {
			helpLine = commandInterested + " <keyword>\n";
		} else if (isByeCommand) {
			helpLine = commandInterested;
		} else if (isDoneCommand) {
			helpLine = commandInterested + " <task number>";
		}
		return helpLineStart + helpLine;
	}
	
	// Execute only generally, all commands guide
	private String executeGeneral() {
		String helpLine1 = "Welcome to help page!\n";
		String helpLine2 = "Here are some commands you can use:\n";
		String helpLine3 = "list, todo, event, deadline, done, delete, find, bye";
		return helpLine1 + helpLine2 + helpLine3;
	}
	
	@Override
	public boolean isExit() {
		return false;
	}
}
