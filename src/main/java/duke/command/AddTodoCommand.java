package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Todo;

/**
 * Represents a <code>Command</code> that adds a new <code>Todo</code> object to the <code>TaskList</code>.
 */
public class AddTodoCommand extends Command {
	String details;

	/**
	 * Constructor for <Code>AddTodoCommand</Code>.
	 * @param details The unprocessed details of the <code>Todo</code> task.
	 */
	public AddTodoCommand(String details) {
		super();
		this.details = details;
	}

	/**
	 * Creates a  new <code>Todo</code> object and adds it to the <code>TaskList</code>.
	 * Calls methods in <code>Storage</code> and <code>Ui</code> to write the updated <code>TaskList</code> to hard
	 * disk and print message to console respectively.
	 * @param tasks Instance of <code>TaskList</code> that stores <code>Task</code> objects.
	 * @param ui Instance of <code>Ui</code> that handles user input and output.
	 * @param storage Instance of <code>Storage</code> that handles writing and loading of information to hard disk.
	 * @throws DukeException If provided details are insufficient or invalid.
	 */
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
		if (details.length() == 0) {
			throw new DukeException("\u2639 OOPS!!! The description of a todo cannot be empty.");
		} else {
			Todo todo = new Todo(details);
			tasks.addTask(todo);
			ui.printAddTaskMessage(todo, tasks.getSize());
			try {
				storage.writeToFile(tasks);
			} catch (DukeException exception) {
				ui.printExceptionMessage(exception);
			}
		}
	}

	/**
	 * Checks if the current <code>Command</code> is an <code>ExitCommand</code>.
	 * @return False.
	 */
	public boolean isExit() {
		return false;
	}

	/**
	 * Returns unprocessed details.
	 * @return Unprocessed details.
	 */
	public String getDetails() {
		return details;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (o instanceof AddTodoCommand) {
			AddTodoCommand addTodoCommand = (AddTodoCommand) o;
			return addTodoCommand.getDetails().equals(details);
		} else {
			return false;
		}
	}
}

