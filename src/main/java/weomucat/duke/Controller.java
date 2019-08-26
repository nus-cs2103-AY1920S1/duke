package weomucat.duke;

import weomucat.duke.command.*;
import weomucat.duke.command.listener.*;
import weomucat.duke.exception.DukeException;
import weomucat.duke.exception.UnknownCommandException;
import weomucat.duke.task.Task;
import weomucat.duke.ui.listener.UserInputListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller
 */
public class Controller implements UserInputListener {
	private static final String COMMAND_DEADLINE = "deadline";
	private static final String COMMAND_EVENT = "event";
	private static final String COMMAND_TODO = "todo";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_DONE = "done";
	private static final String COMMAND_LIST = "list";
	private static final String COMMAND_BYE = "bye";

	private HashMap<String, Command> commands;
	private ArrayList<AddTaskCommandListener> addTaskCommandListeners;
	private ArrayList<DeleteTaskCommandListener> deleteTaskCommandListeners;
	private ArrayList<DoneTaskCommandListener> doneTaskCommandListeners;
	private ArrayList<ListTaskCommandListener> listTaskCommandListeners;
	private ArrayList<ByeCommandListener> byeCommandListeners;

	public Controller() {
		this.addTaskCommandListeners = new ArrayList<>();
		this.deleteTaskCommandListeners = new ArrayList<>();
		this.doneTaskCommandListeners = new ArrayList<>();
		this.listTaskCommandListeners = new ArrayList<>();
		this.byeCommandListeners = new ArrayList<>();

		this.commands = new HashMap<>();
		this.commands.put(COMMAND_DEADLINE, new DeadlineCommand() {
			@Override
			public void updateListeners(Task task) throws DukeException {
				for (AddTaskCommandListener listener : addTaskCommandListeners) {
					listener.addTaskCommandUpdate(task);
				}
			}
		});
		this.commands.put(COMMAND_EVENT, new EventCommand() {
			@Override
			public void updateListeners(Task task) throws DukeException {
				for (AddTaskCommandListener listener : addTaskCommandListeners) {
					listener.addTaskCommandUpdate(task);
				}
			}
		});
		this.commands.put(COMMAND_TODO, new TodoCommand() {
			@Override
			public void updateListeners(Task task) throws DukeException {
				for (AddTaskCommandListener listener : addTaskCommandListeners) {
					listener.addTaskCommandUpdate(task);
				}
			}
		});
		this.commands.put(COMMAND_DELETE, new DeleteCommand() {
			@Override
			public void updateListeners(int i) throws DukeException {
				for (DeleteTaskCommandListener listener : deleteTaskCommandListeners) {
					listener.deleteTaskCommandUpdate(i);
				}
			}
		});
		this.commands.put(COMMAND_DONE, new DoneCommand() {
			@Override
			public void updateListeners(int i) throws DukeException {
				for (DoneTaskCommandListener listener : doneTaskCommandListeners) {
					listener.doneTaskCommandUpdate(i);
				}
			}
		});
		this.commands.put(COMMAND_LIST, new ListCommand() {
			@Override
			public void updateListeners() {
				for (ListTaskCommandListener listener : listTaskCommandListeners) {
					listener.listTaskCommandUpdate();
				}
			}
		});
		this.commands.put(COMMAND_BYE, new ByeCommand() {
			@Override
			public void updateListeners() {
				for (ByeCommandListener listener : byeCommandListeners) {
					listener.byeCommandUpdate();
				}
			}
		});
	}

	public void newAddTaskCommandListener(AddTaskCommandListener listener) {
		this.addTaskCommandListeners.add(listener);
	}

	public void newDeleteTaskCommandListener(DeleteTaskCommandListener listener) {
		this.deleteTaskCommandListeners.add(listener);
	}

	public void newDoneTaskCommandListener(DoneTaskCommandListener listener) {
		this.doneTaskCommandListeners.add(listener);
	}

	public void newListTaskCommandListener(ListTaskCommandListener listener) {
		this.listTaskCommandListeners.add(listener);
	}

	public void newByeCommandListener(ByeCommandListener listener) {
		this.byeCommandListeners.add(listener);
	}

	@Override
	public void userInputUpdate(String userInput) throws DukeException {
		// Initialize parser for this line of user input.
		Parser parser = new Parser(userInput);

		// Get the command of the user input.
		String commandString = parser.nextCommand();

		// Resolve the string command to a Command object.
		Command command = commands.get(commandString);

		// Command not known, throw an exception.
		if (command == null) {
			throw new UnknownCommandException();
		}

		// Get what parameters the command accepts.
		String[] parameterOptions = command.getParameterOptions();

		// Parse the parameters from user input.
		HashMap<String, String> parameters = parser.nextParameters(parameterOptions);

		// Set the parameters to the Command object.
		command.setParameters(parameters);

		// Finally, run the command.
		command.run();
	}
}
