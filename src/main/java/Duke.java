import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Duke {

	private static final String[] initialGreeting = new String[]{
		"Hello! I'm Duke",
		"What can I do for you?"
	};

	private DukeIO io;
	private final List<Task> taskList;

	private void addTask(Task t) {
		this.taskList.add(t);
		this.io.say(
				"Got it. I've added this task:",
				"  " + t,
				String.format("Now you have %d task%s in the list.",
					this.taskList.size(),
					this.taskList.size() == 1 ? "" : "s")
				);
	}

	private boolean handleBye(Command input) {
		this.io.say("Bye. Hope to see you again soon!");
		return true;
	}

	private boolean displayList(Command input) {
		this.io.say("Here are the tasks in your list:");
		this.io.say(this.taskList.stream()
				.map(Object::toString)
				.map(new CounterDecorator(1))
				.iterator());
		return false;
	}

	private boolean makeDeadlineTask(Command input) {
		String description = input.arguments;
		String deadline = input.namedArguments.get("by");
		if(deadline == null) {
			deadline = "unknown";
		}

		DeadlineTask task = new DeadlineTask(description, deadline);

		this.addTask(task);
		return false;
	}
	private boolean makeToDoTask(Command input) {
		this.addTask(new Task(input.arguments));
		return false;
	}

	private boolean markAsDone(Command input) {
		int index;
		try {
			index = Integer.parseInt(input.arguments);
		} catch(NumberFormatException e) {
			this.io.say("Index provided was not an integer!");
			return false;
		}

		if(index < 0 || index > this.taskList.size()) {
			this.io.say("There's no task with that index!");
			return false;
		}

		Task selectedTask = this.taskList.get(index-1);
		selectedTask.markComplete();
		this.io.say("Nice! I've marked this task as done:",
			"  " + selectedTask);
		return false;
	}

	private Duke() {
		this.io = new DukeIO();
		//Bind command handlers
		this.io.bindCommand("list", this::displayList);
		this.io.bindCommand("done", this::markAsDone);
		this.io.bindCommand("bye", this::handleBye);
		this.io.bindCommand("deadline", this::makeDeadlineTask);
		this.io.bindCommand("todo", this::makeToDoTask);

		this.taskList = new ArrayList<>();
	}

	private void run() {
		//Start off greeting the user.
		this.io.withDialogBlock(() -> this.io.say(initialGreeting));

		//Start listen loop.
		this.io.listen();
	}

    public static void main(String[] args) {
		Duke duke = new Duke();
		duke.run();
    }
}
