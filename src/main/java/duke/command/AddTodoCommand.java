package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Todo;

public class AddTodoCommand extends Command {

	String details;

	public AddTodoCommand(String details) {
		super();
		this.details = details;
	}

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

	public boolean isExit() {
		return false;
	}

}

