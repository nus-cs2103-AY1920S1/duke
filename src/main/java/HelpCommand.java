/**
 * Display all the commands available.
 */
public class HelpCommand {

	public HelpCommand() {

	}

	/**
	 * Prints out all the available commands for Duke.
	 * @return Description and format of the commands.
	 */
	public static String printHelpCommand(){
		String toDoCommandLine = Ui.BORDER + "\nCreate a todo task.\nCommand: todo <description>\n";
		String deadLineCommandLine = "\nCreate a deadline task.\nCommand: deadline <description> /by <deadline>\n";
		String eventCommandLine = "\nCreate and event.\nCommand: event <description> /at <venue/time>\n";
		String findCommandLine = "\nFind a task.\nCommand: find <keyword>\n";
		String statsCommandLine = "\nGet the stats of your task.\nCommand: stats\n";
		String doneCommandLine = "\nMark a task as complete.\nCommand: done <index of task>\n";
		String deleteCommandLine = "\nDelete a task.\nCommand: delete <index of task>\n";
		String listCommandLine = "\nList out all your task.\nCommand: list\n" ;
		String dateFormat = "\nFormat of date: dd/mm/yyyy.\n";
		String timeFormat = "\nFormat of time: hhhh\n" + Ui.BORDER;
		StringBuilder allCommands = new StringBuilder();
		allCommands.append(toDoCommandLine + deadLineCommandLine + eventCommandLine + findCommandLine
				                   + statsCommandLine + doneCommandLine + deleteCommandLine + listCommandLine);
		allCommands.append(dateFormat + timeFormat);

		return allCommands.toString();
	}
}
