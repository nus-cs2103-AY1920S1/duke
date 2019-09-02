import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.Date;

class AddCommand extends Command {
	private static SimpleDateFormat formatterIn = new SimpleDateFormat("d/M/yyyy HHmm");
	private static SimpleDateFormat formatterOut = new SimpleDateFormat("d MMM yyyy ha");

	public AddCommand(String command, String remainingCommand) {
		super(command, remainingCommand);
	}

	/**
	 * Adds new task to list
	 * @param tasks to add
	 * @param ui to print
	 */

	public void execute(TaskList tasks, Ui ui) throws DukeException, ParseException {
		tasks.addTask(createTask(command, false, remainingCommand));

		ui.print("Got it. I've added this task:\n  " + task.toString());
		ui.print("Now you have " + tasks.getList().size() + " tasks in the list.");
	}

	/**
	 * Caches task associated with command, returns it when queried
	 * @return Task to be added using the addCommand
	 * @throws DukeException  could be thrown by createTask
	 * @throws ParseException could be thrown by could be thrown by unparseable datetime in createTask
	 */

	public Task getTask() throws DukeException, ParseException { // only called when loading
		if (this.task == null) {
			// remainingCommand contains [1/0] [description]
			String[] parts = remainingCommand.split(" ");
			int binary = Integer.valueOf(parts[0]);
			String description = parts[1];
			boolean isDone = (binary == 1);
			this.task = createTask(command, isDone, description);
		}

		return this.task;
	}

	/**
	 * Parses command to create task by type with its details, reused by loading
	 * @param type of task
	 * @param isDone
	 * @param description of task, deadline, details, etc
	 * @return Task created by command
	 * @throws DukeException  could be thrown by loading invalid tasks
	 * @throws ParseException could be thrown by unparseable datetime in loading functions
	 */
	
	public Task createTask(String type, boolean isDone, String description) throws DukeException, ParseException {
		// depending on type
		switch (type) {
		case "todo":
			if (description.isEmpty()) {
				throw new DukeException("☹ OOPS!!! The description of a " + type + " cannot be empty.");
			}
			this.task = loadTodo(isDone, description);
			break;

		case "deadline":
			if (description.isEmpty()) {
				throw new DukeException("☹ OOPS!!! The description of a " + type + " cannot be empty.");
			}
			this.task = loadDeadline(isDone, description);
			break;

		case "event":
			if (description.isEmpty()) {
				throw new DukeException("☹ OOPS!!! The description of a " + type + " cannot be empty.");
			}
			this.task = loadEvent(isDone, description);
			break;

		default:
			throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
		}

		if (this.task == null) {
			throw new DukeException("There is no new task!");
		}

		return this.task;
	}

	private Todo loadTodo(boolean isDone, String description) {
		Todo todo = new Todo(isDone, description);
		return todo;
	}

	private Deadline loadDeadline (boolean isDone, String description) throws DukeException, ParseException {
		int indexBy = description.indexOf("/"); // potential source of error
		if (indexBy == -1) {
			throw new DukeException("Put / before by!");
		}
		String taskDesc = description.substring(0, indexBy - 1); // start after space, end before space before /

		String dateInString = description.substring(indexBy + 4);
		Date date = formatterIn.parse(dateInString);
		String by = formatterOut.format(date);

		Deadline dead = new Deadline(isDone, taskDesc, by);
		return dead;
	}

	private Event loadEvent(boolean isDone, String description) throws DukeException, ParseException {
		int indexAt = description.indexOf("/");
		if (indexAt == -1) {
			throw new DukeException("Put / before at!");
		}
		String taskDesc = description.substring(0, indexAt - 1); // start after space, end before space before /

		String dateInString = description.substring(indexAt + 4);
		Date date = formatterIn.parse(dateInString);
		String at = formatterOut.format(date);

		Event event = new Event(isDone, taskDesc, at);
		return event;
	}
}