import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Duke {

	private static final String[] initialGreeting = new String[]{
		"Hello! I'm Duke",
		"What can I do for you?"
	};

	private static final String lineRule =
		"____________________________________________________________";

	private static void say(String... lines) {
		say(Arrays.asList(lines).iterator());
	}
	private static void say(Iterator<String> lines) {
		//Print start of reply line
		System.out.println(lineRule);

		//Print each given line
		while(lines.hasNext()) {
			System.out.println(lines.next());
		}

		//Print end of reply line, and extra empty line
		System.out.println(lineRule);
		System.out.println();
	}

	static class CounterDecorator implements Function<String, String> {
		private int counter;
		public CounterDecorator(int start) {
			this.counter = start;
		}
		public String apply(String str) {
			String ret = String.format("%d: %s", this.counter, str);
			this.counter++;
			return ret;
		}
	}

	private static final Map<String, Predicate<String>> commandMap
		= new HashMap<>();
	private static Predicate<String> defaultHandler;

	private static final List<Task> taskList = new ArrayList<>();

	private static boolean handleBye(String input) {
		say("Bye. Hope to see you again soon!");
		return true;
	}

	private static boolean displayList(String input) {
		say(Duke.taskList.stream()
				.map(Object::toString)
				.map(new CounterDecorator(1))
				.iterator());
		return false;
	}

	private static boolean addTask(String input) {
		Duke.taskList.add(new Task(input));
		say("added: " + input);
		return false;
	}

	private static boolean markAsDone(String input) {
		int index;
		try {
			index = Integer.parseInt(input);
		} catch(NumberFormatException e) {
			say("Index provided was not an integer!");
			return false;
		}

		if(index < 0 || index > Duke.taskList.size()) {
			say("There's no task with that index!");
			return false;
		}

		Task selectedTask = Duke.taskList.get(index-1);
		selectedTask.markComplete();
		say("Nice! I've marked this task as done:",
			"  " + selectedTask);
		return false;
	}

    public static void main(String[] args) {
		//Bind command handlers
		Duke.commandMap.put("list", Duke::displayList);
		Duke.commandMap.put("done", Duke::markAsDone);
		Duke.commandMap.put("bye", Duke::handleBye);
		Duke.defaultHandler = Duke::addTask;

		//Initialize input.
		Scanner s = new Scanner(System.in);

		//Start off greeting the user.
		say(initialGreeting);

		//While there is still input from user
		while(s.hasNextLine()) {
			//Read single line of user input, and remove extra spaces
			String userInput = s.nextLine().trim();

			int spacePos = userInput.indexOf(' ');
			String command, rest;
			if(spacePos == -1) {
				command = userInput;
				rest = null;
			} else {
				command = userInput.substring(0, spacePos);
				rest = userInput.substring(spacePos+1);
			}

			Predicate<String> cmdHandler = commandMap.get(command);
			boolean shouldExit;
			if(cmdHandler != null) {
				shouldExit = cmdHandler.test(rest);
			} else {
				shouldExit = defaultHandler.test(userInput);
			}

			if(shouldExit) {
				break;
			}
		}
    }
}
