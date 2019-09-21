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

	public Parser(Ui ui, TaskList tasks, DukeReadFile rf, DukeWriteFile wf) {
		this.ui = ui;
		this.tasks = tasks;
		this.rf = rf;
		this.wf = wf;
	}

	/**
	 * Main bullk of the program where evaluation of the input
	 * by the user takes place.
	 *
	 * @param input Input of the tasks of the user.
	 */
	public String evaluate(String input) {
		StringBuilder storedText = new StringBuilder();
		try {
			if (input.equals("bye")) {
				storedText.append(Ui.OUTRO);
			} else if (input.startsWith("find")) {
				Command findTask = new FindCommand(input);
				String taskFound = findTask.execute(tasks, wf);
				storedText.append(taskFound);

			} else if (input.equals("list")) {
				Command listTask = new ListCommand(input);
				String listOfTask = listTask.execute(tasks, wf);
				storedText.append(listOfTask);

			} else if (input.startsWith("done")) {
				Command doneTask = new DoneCommand(input);
				String completedTask = doneTask.execute(tasks, wf);
				storedText.append(completedTask);

			} else if (input.startsWith("deadline")) {
				Deadline deadLineTask = Deadline.createDeadLine(input);
				tasks.addTask(deadLineTask);
				storedText.append(deadLineTask.execute(deadLineTask, wf, tasks));

			} else if (input.startsWith("event")) {
				Event eventTask = Event.createEvent(input);
				tasks.addTask(eventTask);
				storedText.append(eventTask.execute(eventTask, wf, tasks));

			} else if (input.startsWith("todo")) {
				Todo toDoTask = Todo.createToDo(input);
				tasks.addTask(toDoTask);
				storedText.append(toDoTask.execute(toDoTask, wf, tasks));

			} else if (input.startsWith("delete")) {
				Command taskToDelete = new DeleteCommand(input);
				String deletedTask = taskToDelete.execute(tasks, wf);
				storedText.append(deletedTask);

			} else if (input.startsWith("stats")) {
				Command taskStats = new StatisticsCommand();
				String stats = taskStats.execute(tasks, wf);
				storedText.append(stats);
			} else if (input.startsWith("help")) {
				storedText.append(HelpCommand.printHelpCommand());
			}
			else {
				throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
			}
		} catch (DukeException e) {
			storedText.append(Ui.BORDER + "\n" + e + "\n" + Ui.BORDER);
		} catch (IOException e) {
			storedText.append(Ui.BORDER + "\n" + e + "\n" + Ui.BORDER);
		}

		return storedText.toString();
	}

}
