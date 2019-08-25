package weomucat.duke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static weomucat.duke.Parser.PARAMETER_DEFAULT;

public class Duke {
	private static final String COMMAND_TODO = "todo";
	private static final String COMMAND_EVENT = "event";
	private static final String COMMAND_DEADLINE = "deadline";
	private static final String COMMAND_LIST = "list";
	private static final String COMMAND_DONE = "done";
	private static final String COMMAND_DELETE = "delete";
	private static final String COMMAND_BYE = "bye";

	private static final String PARAMETER_AT = "/at";
	private static final String PARAMETER_BY = "/by";

	private static final String SAY_INDENTATION = "\t";
	private static final String SAY_HORIZONTAL_LINE = "============================================================";

	private Scanner scanner;
	private Storage storage;
	private ArrayList<Task> tasks;

	public static void main(String[] args) {
		new Duke();
	}

	public Duke() {
		String logo = " ____        _        \n"
				+ "|  _ \\ _   _| | _____ \n"
				+ "| | | | | | | |/ / _ \\\n"
				+ "| |_| | |_| |   <  __/\n"
				+ "|____/ \\__,_|_|\\_\\___|\n";
		System.out.println("Hello from\n" + logo);

		// Scanner to read stdin
		this.scanner = new Scanner(System.in);

		// Initialize storage for saving and loading tasks.
		this.storage = new Storage("data/tasks");

		// Initialize tasks ArrayList
		try {
			this.tasks = this.storage.load();
			say("Loaded tasks from disk.");
		} catch (StorageException e) {
			say("\u2639 OOPS!!! " + e.getMessage());
			this.tasks = new ArrayList<>();
		}

		// Greet user
		say("Hello! I'm Duke", "What can I do for you?");

		boolean running = true;
		while (running) {
			try {
				running = run();
			} catch (DukeException e) {
				say("\u2639 OOPS!!! " + e.getMessage());
			}
		}
	}

	private boolean run() throws DukeException {
		// Ensure stdin has next line
		if (!this.scanner.hasNextLine()) {
			return false;
		}

		// Initialize Parser for new line (user input)
		Parser parser = new Parser(this.scanner.nextLine());

		// Get command of user input
		String command = parser.nextCommand();

		// Run actions based on which command the user entered.
		switch (command) {

			// Add tasks
			case COMMAND_TODO: {
				String description;
				HashMap<String, String> parameters = parser.nextParameters();

				if (parameters.containsKey(PARAMETER_DEFAULT)) {
					description = parameters.get(PARAMETER_DEFAULT);
				} else {
					throw new InvalidParameterException("The description of a todo cannot be empty.");
				}

				addTask(new TodoTask(description));
			}
			break;

			case COMMAND_EVENT: {
				String description;
				String at;
				HashMap<String, String> parameters = parser.nextParameters(PARAMETER_AT);

				if (parameters.containsKey(PARAMETER_DEFAULT)) {
					description = parameters.get(PARAMETER_DEFAULT);
				} else {
					throw new InvalidParameterException("The description of an event cannot be empty.");
				}

				if (parameters.containsKey(PARAMETER_AT)) {
					at = parameters.get(PARAMETER_AT);
				} else {
					throw new InvalidParameterException("The location of an event cannot be empty.");
				}

				addTask(new EventTask(description, at));
			}
			break;

			case COMMAND_DEADLINE: {
				String description;
				String by;
				HashMap<String, String> parameters = parser.nextParameters(PARAMETER_BY);

				if (parameters.containsKey(PARAMETER_DEFAULT)) {
					description = parameters.get(PARAMETER_DEFAULT);
				} else {
					throw new InvalidParameterException("The description of a deadline cannot be empty.");
				}

				if (parameters.containsKey(PARAMETER_BY)) {
					by = parameters.get(PARAMETER_BY);
				} else {
					throw new InvalidParameterException("The date of a deadline cannot be empty.");
				}

				addTask(new DeadlineTask(description, by));
			}
			break;

			// List all tasks.
			case COMMAND_LIST: {
				ArrayList<String> out = new ArrayList<>();
				out.add("Here are the tasks in your list:");

				for (int i = 0; i < this.tasks.size(); i++) {
					// Get task from tasks
					Task task = this.tasks.get(i);

					// Format task with no. in front
					out.add(String.format("%d. %s", i + 1, task));
				}

				say(out.toArray(new String[0]));
			}
			break;

			// Mark a task as done.
			case COMMAND_DONE: {
				Task task;
				String index;
				HashMap<String, String> parameters = parser.nextParameters();

				if (parameters.containsKey(PARAMETER_DEFAULT)) {
					index = parameters.get(PARAMETER_DEFAULT);
				} else {
					throw new InvalidParameterException("The index cannot be empty.");
				}

				try {
					// Get index of task
					int i = Integer.parseInt(index) - 1;

					// Get task from tasks
					task = this.tasks.get(i);

					// Set task to done
					task.setDone(true);
				} catch (NumberFormatException e) {
					throw new InvalidParameterException("The index is not a valid number.");
				} catch (IndexOutOfBoundsException e) {
					throw new InvalidParameterException("That is not a valid index of a task.");
				}

				say("Nice! I've marked this task as done:", task.toString());

				this.storage.save(this.tasks);
			}
			break;

			// Delete a task from tasks.
			case COMMAND_DELETE: {
				Task task;
				String index;
				HashMap<String, String> parameters = parser.nextParameters();

				if (parameters.containsKey(PARAMETER_DEFAULT)) {
					index = parameters.get(PARAMETER_DEFAULT);
				} else {
					throw new InvalidParameterException("The index cannot be empty.");
				}

				try {
					// Get index of task
					int i = Integer.parseInt(index) - 1;

					// Get task from tasks
					task = this.tasks.get(i);

					// Remove task
					this.tasks.remove(i);
				} catch (NumberFormatException e) {
					throw new InvalidParameterException("The index is not a valid number.");
				} catch (IndexOutOfBoundsException e) {
					throw new InvalidParameterException("That is not a valid index of a task.");
				}

				say("Noted. I've removed this task:",
						task.toString(),
						String.format("Now you have %d task(s) in the list.", this.tasks.size()));

				this.storage.save(this.tasks);
			}
			break;

			// Terminate the program.
			case COMMAND_BYE:
				say("Bye. Hope to see you again soon!");
				return false;

			// Unknown command.
			default:
				throw new UnknownCommandException();
		}

		return true;
	}

	private void say(String... lines) {
		System.out.println(SAY_INDENTATION + SAY_HORIZONTAL_LINE);
		for (String line : lines) {
			System.out.println(SAY_INDENTATION + line);
		}
		System.out.println(SAY_INDENTATION + SAY_HORIZONTAL_LINE);
	}

	private void addTask(Task task) throws StorageException {
		this.tasks.add(task);
		say("Got it. I've added this task:",
				task.toString(),
				String.format("Now you have %d task(s) in the list.", this.tasks.size()));

		this.storage.save(this.tasks);
	}
}
