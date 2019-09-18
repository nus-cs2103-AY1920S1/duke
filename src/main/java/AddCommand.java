import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.Date;

class AddCommand extends Command {
	private static SimpleDateFormat formatterIn = new SimpleDateFormat("d/M/yyyy HHmm");
	private static SimpleDateFormat formatterOut = new SimpleDateFormat("d MMM yyyy ha");

	private boolean isLoading = false;

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

		ui.print("Got it. I've added this task:\n  " + task.toString() + "\n");
		ui.print("Now you have " + tasks.getList().size() + " tasks in the list.\n");
	}

	/**
	 * Caches task associated with command, returns it when queried
	 * Only called when loading tasks from storage
	 * @return Task to be added to list using the addCommand
	 * @throws DukeException  could be thrown by createTask
	 * @throws ParseException thrown when datetime is unparseable in createTask
	 */

	public Task getTask() throws DukeException, ParseException {
		if (this.task == null) {
			// remainingCommand contains [1/0] [description]
			isLoading = true;
			String[] parts = remainingCommand.split(" ");
			int binary = Integer.valueOf(parts[0]);
			String description = remainingCommand.substring(2);
			boolean isDone = (binary == 1);
			this.task = createTask(command, isDone, description);
		}

		isLoading = false;
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
				throw new DukeException("☹ OOPS!!! The description of a " + type + " cannot be empty.\n");
			}
			this.task = loadTodo(isDone, description);
			break;

		case "deadline":
			if (description.isEmpty()) {
				throw new DukeException("☹ OOPS!!! The description of a " + type + " cannot be empty.\n");
			}
			this.task = loadDeadline(isDone, description);
			break;

		case "event":
			if (description.isEmpty()) {
				throw new DukeException("☹ OOPS!!! The description of a " + type + " cannot be empty.\n");
			}
			this.task = loadEvent(isDone, description);
			break;

		default:
			throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
		}

		if (this.task == null) {
			throw new DukeException("There is no new task!\n");
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
			throw new DukeException("Put / before by!\n");
		} else if (indexBy == 0) {
			throw new DukeException("Please include task description!\n");
		}
		String taskDesc = description.substring(0, indexBy - 1); // start after space, end before space before /

		String dateInString = description.substring(indexBy + 4);

		Date date;
		String by;
		if (isLoading) {
			by = dateInString;
		} else {
			date = formatterIn.parse(dateInString);
			by = formatterOut.format(date);
		}

		Deadline dead = new Deadline(isDone, taskDesc, by);
		return dead;
	}

	private Event loadEvent(boolean isDone, String description) throws DukeException, ParseException {
		int indexAt = description.indexOf("/");
		if (indexAt == -1) {
			throw new DukeException("Put / before at!\n");
		} else if (indexAt == 0) {
			throw new DukeException("Please include task description!\n");
		}
		String taskDesc = description.substring(0, indexAt - 1); // start after space, end before space before /

		String dateInString = description.substring(indexAt + 4);

		Date date;
		String at;
		if (isLoading) {
			at = dateInString;
		} else {
			date = formatterIn.parse(dateInString);
			at = formatterOut.format(date);
		}

		Event event = new Event(isDone, taskDesc, at);
		return event;
	}
}