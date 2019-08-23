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

	private boolean handleBye(String input) {
		this.io.say("Bye. Hope to see you again soon!");
		return true;
	}

	private boolean displayList(String input) {
		this.io.say(this.taskList.stream()
				.map(Object::toString)
				.map(new CounterDecorator(1))
				.iterator());
		return false;
	}

	private static final String bySwitch = " /by ";
	private boolean addDeadlineTask(String input) {
		int deadlineIndex = input.indexOf(bySwitch);
		String description = input.substring(0, deadlineIndex);
		String deadline = input.substring(deadlineIndex + bySwitch.length());
		DeadlineTask task = new DeadlineTask(description, deadline);
		this.taskList.add(task);
		this.io.say("added: " + task);
		return false;
	}

	private boolean addTask(String input) {
		this.taskList.add(new Task(input));
		this.io.say("added: " + input);
		return false;
	}

	private boolean markAsDone(String input) {
		int index;
		try {
			index = Integer.parseInt(input);
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
		this.io.bindCommand("deadline", this::addDeadlineTask);
		this.io.setUnknownCommandHandler(this::addTask);

		this.taskList = new ArrayList<>();
	}

	private void run() {
		//Start off greeting the user.
		this.io.say(initialGreeting);

		//Start listen loop.
		this.io.listen();
	}

    public static void main(String[] args) {
		Duke duke = new Duke();
		duke.run();
    }
}
