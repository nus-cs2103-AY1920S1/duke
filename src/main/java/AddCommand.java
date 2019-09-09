import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Represents the addition of tasks. Could be one of 3 types of tasks: todo, deadline, event
 */
public class AddCommand extends Command {
	private String commandType;

	/**
	 * Constructor of AddCommand object
	 * @param command the command input. Used to distinguish the type of task to create and add
	 * @param inFullCommandScanner scanner to read the rest of the user's input including the taskDescription and other
	 *                             details
	 */
	public AddCommand(String command, Scanner inFullCommandScanner) {
		super(inFullCommandScanner);
		this.commandType = command;
	}

	/**
	 * Execute the saving of tasks into TaskList and printing out a confirmation or error message through Ui object
	 * @param tasks represents all the tasks the user has input in memory
	 * @param ui represents the UI object used to interact with the user be it reading input or displaying messages
	 * @param storage represents the storage object to read and write to the archival file. Not used in this method
	 * @throws DukeException throws Exception for invalid formatting of user commands
	 */
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		if (!inFullCommandScanner.hasNext()) {
			throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
		} else {
			if (commandType.equals("todo")) {
				String taskDescription = inFullCommandScanner.nextLine().trim();
				assert !taskDescription.isEmpty();
				Task addedTodo = tasks.addTask(new ToDo(taskDescription));
				ui.alertLatestTaskAdded(addedTodo, tasks);
			} else if (commandType.equals("deadline")) {
				StringBuilder deadLineTaskName = new StringBuilder();
				StringBuilder deadLineTaskDateTime = new StringBuilder();
				String currentWord;
				Boolean reachedDateTimePortion = false;
				while (inFullCommandScanner.hasNext()) {
					currentWord = inFullCommandScanner.next();
					if (currentWord.equals("/by")) {
						//set switch to true but do not append
						reachedDateTimePortion = true;
					} else if (reachedDateTimePortion == false) {
						deadLineTaskName.append(currentWord);
						deadLineTaskName.append(" ");
					} else {
						deadLineTaskDateTime.append(currentWord);
						deadLineTaskDateTime.append(" ");
					}
				}
				if (!reachedDateTimePortion) {
					throw new DukeException("/by keyword not found");
				}
				if (deadLineTaskDateTime.length() == 0) {
					throw new DukeException("no date provided");
				}
				assert !(deadLineTaskName == null);
				assert !(deadLineTaskDateTime == null);
				DeadLine deadline = new DeadLine(deadLineTaskName.toString().trim(),
					LocalDateTime.parse(formatDateTimeString(deadLineTaskDateTime.toString().trim()),
						dateTimeFormatter));
				Task addedDeadline = tasks.addTask(deadline);
				ui.alertLatestTaskAdded(addedDeadline, tasks);
			} else if (commandType.equals("event")) {
				StringBuilder eventName = new StringBuilder();
				StringBuilder eventDateTime = new StringBuilder();
				String currentWord;
				Boolean reachedDateTimePortion = false;
				while (inFullCommandScanner.hasNext()) {
					currentWord = inFullCommandScanner.next();
					if (currentWord.equals("/at")) {
						//set switch to true but do not append
						reachedDateTimePortion = true;
					} else if (reachedDateTimePortion == false) {
						eventName.append(currentWord);
						eventName.append(" ");
					} else {
						eventDateTime.append(currentWord);
						eventDateTime.append(" ");
					}
				}
				if (!reachedDateTimePortion) {
					throw new DukeException("/at keyword not found");
				}
				if (eventDateTime.length() == 0) {
					throw new DukeException("no date provided");
				}
				assert !(eventName == null);
				assert !(eventDateTime == null);
				Event event = new Event(eventName.toString().trim(),
					LocalDateTime.parse(formatDateTimeString(eventDateTime.toString().trim()), dateTimeFormatter));
				Task eventAdded = tasks.addTask(event);
				ui.alertLatestTaskAdded(eventAdded, tasks);
			} else {
				throw new DukeException("task type not found");
			}
		}
	}

	private static String formatDateTimeString(String eventString) throws DukeException {
		StringBuilder formattedDateTime = new StringBuilder();
		try {
			String[] splitDateAndTime = eventString.split(" ");
			String date = splitDateAndTime[0];
			String time = splitDateAndTime[1];
			String[] splitDate = date.split("/");
			formattedDateTime.append(String.format("%02d", Integer.parseInt(splitDate[0])));
			formattedDateTime.append("/");
			formattedDateTime.append(String.format("%02d", Integer.parseInt(splitDate[1])));
			formattedDateTime.append("/");
			formattedDateTime.append(splitDate[2]);
			formattedDateTime.append(" ");
			formattedDateTime.append(time);
			return formattedDateTime.toString();
		} catch (Exception ex) {
			throw new DukeException("the date time format entered is incorrect. " +
				"Please enter again in the following format: dd/MM/yyyy HHmm");
		}
	}
}
