import java.time.LocalDateTime;
import java.util.Scanner;

public class AddCommand extends Command {
	private String commandType;

	public AddCommand(String command, Scanner inFullCommandScanner) {
		super(inFullCommandScanner);
		this.commandType = command;
	}

	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
		if(!inFullCommandScanner.hasNext()) {
			throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
		} else {
			if (commandType.equals("todo")) {
				String taskDescription = inFullCommandScanner.nextLine().trim();
				Task addedTodo = tasks.addTask(new ToDo(taskDescription));
				ui.alertLatestTaskAdded(addedTodo, tasks);
			} else if (commandType.equals("deadline")) {
				StringBuilder deadLineTaskName = new StringBuilder();
				StringBuilder deadLineTaskDateTime = new StringBuilder();
				String currentWord;
				Boolean reachedDateTimePortion = false;
				while(inFullCommandScanner.hasNext()) {
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
				while(inFullCommandScanner.hasNext()) {
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
