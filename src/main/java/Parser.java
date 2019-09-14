import java.io.IOException;

/**
 * Deals with making sense of the user command and formats
 * the input by the user to a specified format.
 */
public class Parser {
	private Ui ui;
	private TaskList tasks;
	private DukeReadFile rf;
	private DukeWriteFile wf;
	private boolean isEndLoop;

	public Parser(Ui ui, TaskList tasks, DukeReadFile rf, DukeWriteFile wf) {
		this.ui = ui;
		this.tasks = tasks;
		this.rf = rf;
		this.wf = wf;
		isEndLoop = false;
	}

	/**
	 * Main bullk of the program where evaluation of the input
	 * by the user takes place.
	 *
	 * @param input Input of the tasks of the user.
	 */
	public String evaluate(String input) throws DukeException {
		StringBuilder storedText = new StringBuilder();
		int counter = 0;
		if (input.equals("bye")) {
			isEndLoop = true;
			storedText.append(Ui.OUTRO);
		} else if (input.startsWith("find")) {

			if (input.length() < 5) {
				throw new DukeException(Ui.EMPTY_INPUT);
			}
			String findTask = input.substring(5);
			storedText.append(Ui.BORDER + "\nHere are the matching tasks in your list:\n");
			int taskNumber = 1;
			for (int i = 0; i < tasks.getTaskList().size(); i++) {
				if (tasks.getTask(i).toString().contains(findTask)) {
					storedText.append(taskNumber + "." + tasks.getTask(i).toString() + "\n");
					taskNumber++;
				}
			}
			storedText.append(Ui.BORDER);

		} else if (input.equals("list")) {
			int itemNumber = 1;
			counter = tasks.getCounter();
			if (counter == 0) {
				storedText.append(Ui.NO_TASK);
			} else {
				storedText.append(Ui.OUTPUT_TASK_LIST);
				for (int i = 0; i < counter; i++) {
					storedText.append(itemNumber + "." + tasks.getTask(i).toString() + "\n");
					itemNumber++;
				}
				storedText.append(Ui.BORDER);
			}

		} else if (input.startsWith("done")) {
			try {
				String[] taskDone = input.split(" ");
				if (taskDone.length == 1) {
					throw new DukeException(Ui.WRONG_OP);
				}
				if (taskDone.length > 2) {
					throw new DukeInvalidArgumentException(Ui.DONE_FORMAT, input);
				}
				int taskIndex = Integer.parseInt(taskDone[1]);
				assert taskIndex != 0 : "0 is a wrong input";
				tasks.getTask(taskIndex - 1).markAsDone();
				storedText.append(Ui.MARK_DONE);
				storedText.append(tasks.getTask(taskIndex - 1).toString() + "\n" + Ui.BORDER);
				wf.writeToFile(DukeWriteFile.writeFile(tasks.getTaskList()));

			} catch (IndexOutOfBoundsException e) {
				storedText.append(Ui.BORDER + "\nTask number not found! Try again!\n" + Ui.BORDER);

			} catch (NumberFormatException e) {
				storedText.append(Ui.BORDER + "\nOOPS!! Please input a task number!\n" + Ui.BORDER);

			} catch (IOException e) {
				storedText.append(Ui.BORDER + "\n" + e + "\n" + Ui.BORDER);

			}
		} else if (input.startsWith("deadline")) {
			try {
				Deadline deadLineTask = Deadline.createDeadLine(input);
				tasks.addTask(deadLineTask);
				storedText.append(Ui.BORDER + "\nGot it. I've added this task:\n");
				storedText.append(deadLineTask.toString() + "\n");
				storedText.append("Now you have " + tasks.getCounter() + " tasks in the list.\n" + Ui.BORDER);
				wf.appendToFile("D~" + deadLineTask.getStatus() + "~" +
						                deadLineTask.getDescription() + "~" + deadLineTask.getDeadline() + "\n");

			} catch (IOException e) {
				storedText.append(Ui.BORDER + "\n" + e + "\n" + Ui.BORDER);
			}

		} else if (input.startsWith("event")) {
			try {
				Event eventTask = Event.createEvent(input);
				tasks.addTask(eventTask);
				counter = tasks.getCounter();
				storedText.append(Ui.BORDER + "\nGot it. I've added this task:\n");
				storedText.append(eventTask.toString() + "\n");
				storedText.append("Now you have " + counter + " tasks in the list.\n" + Ui.BORDER);
				wf.appendToFile("E~" + eventTask.getStatus() + "~" +
						                eventTask.getDescription() + "~" + eventTask.getVenue() + "\n");

			} catch (IOException e) {
				storedText.append(Ui.BORDER + "\n" + e + "\n" + Ui.BORDER);
			}

		} else if (input.startsWith("todo")) {
			try {
				Todo toDoTask = Todo.createToDo(input);
				tasks.addTask(toDoTask);
				counter = tasks.getCounter();
				storedText.append(Ui.BORDER + "\nGot it. I've added this task:\n");
				storedText.append(toDoTask.toString() + "\n");
				storedText.append("Now you have " + counter + " tasks in the list.\n" + Ui.BORDER);
				wf.appendToFile("T~" + toDoTask.getStatus() + "~" +
						                toDoTask.getDescription() + "\n");

			} catch (IOException e) {
				storedText.append(Ui.BORDER + "\n" + e + "\n" + Ui.BORDER);
			}

		} else if (input.startsWith("delete")) {
			try {
				String[] taskDelete = input.split(" ");
				if (taskDelete.length == 1) {
					throw new DukeException("☹ OOPS!!! Which task would you like to delete?");
				}

				if (taskDelete.length > 2) {
					throw new DukeInvalidArgumentException(ui.DELETE_FORMAT, input);
				}

				int taskIndex = Integer.parseInt(taskDelete[1]);
				assert taskIndex != 0 : storedText.append("0 is a wrong input");
				String removedTask = tasks.getTask(taskIndex - 1).toString();
				tasks.deleteTask(taskIndex - 1);
				storedText.append(ui.BORDER + "\nNoted. I've removed this task:\n");
				storedText.append(removedTask + "\n");
				counter = tasks.getCounter();
				storedText.append("Now you have " + counter + " tasks in the list.\n" + ui.BORDER);
				wf.writeToFile(DukeWriteFile.writeFile(tasks.getTaskList()));

			} catch (NumberFormatException e) {
				storedText.append(ui.BORDER + "\nOOPS!! Please input a task number!\n" + ui.BORDER);

			} catch (IndexOutOfBoundsException e) {
				storedText.append(ui.BORDER + "\nTask number not found! Try again!\n" + ui.BORDER);

			} catch (IOException e) {
				storedText.append(ui.BORDER + "\n" + e + "\n" + ui.BORDER);

			}
		} else {
			throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
		}

		return storedText.toString();
	}

}
