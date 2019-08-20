import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Task> taskArrayList = new ArrayList<>();

		String logo = " ____        _        \n"
			+ "|  _ \\ _   _| | _____ \n"
			+ "| | | | | | | |/ / _ \\\n"
			+ "| |_| | |_| |   <  __/\n"
			+ "|____/ \\__,_|_|\\_\\___|\n";

		System.out.println("Hello from\n" + logo);
		System.out.println("What can I do for you?");

		String userInput = scanner.nextLine();
		String command = new Scanner(userInput).next();

		while (!command.equals("bye")) {
			try {
				processInput(userInput, taskArrayList);
			} catch (DukeException e) {
				System.out.println(e.getMessage());
			}
			userInput = scanner.nextLine();
			command = new Scanner(userInput).next();
		}
	}

	private static void processInput(String userInput, ArrayList<Task> taskArrayList) throws DukeException {
		Scanner inLineScanner = new Scanner(userInput);
		String command = inLineScanner.next();
		if (userInput.equals("list")) {
			printTaskList(taskArrayList);
		} else if (command.equals("done")) {
			Integer taskNumber = Integer.parseInt(inLineScanner.next()) - 1;
			Task task = taskArrayList.get(taskNumber);
			task.markCompleted();
			StringBuilder sb = new StringBuilder("Nice! I've marked this task as done:\n");
			sb.append("    ");
			sb.append(task.toString());
			System.out.println(sb.toString());
		} else if (command.equals("bye")) {
			System.out.print("Bye. Hope to see you again soon!");
		} else if (command.equals("todo")) {
			if(!inLineScanner.hasNext()) {
				throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
			} else {
				String taskDescription = inLineScanner.nextLine().trim();
				taskArrayList.add(new ToDo(taskDescription));
				alertLatestTaskAdded(taskArrayList);
			}
		} else if (command.equals("deadline")) {
			if (!inLineScanner.hasNext()) {
				//check for deadline description
				throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
			}
			StringBuilder deadLineName = new StringBuilder();
			StringBuilder deadLineDate = new StringBuilder();
			String currentWord;
			Boolean reachedDatePortion = false;
			while(inLineScanner.hasNext()) {
				currentWord = inLineScanner.next();
				if (currentWord.equals("/by")) {
					//set switch to true but do not append
					reachedDatePortion = true;
				} else if (reachedDatePortion == false) {
					deadLineName.append(currentWord);
					deadLineName.append(" ");
				} else {
					deadLineDate.append(currentWord);
					deadLineDate.append(" ");
				}
			}
			if (deadLineDate.length() == 0) {
				throw new DukeException("no date provided");
			}
			taskArrayList.add(new DeadLine(deadLineName.toString().trim(), deadLineDate.toString().trim()));
			alertLatestTaskAdded(taskArrayList);
		} else if (command.equals("event")) {
			if (!inLineScanner.hasNext()) {
				//check for deadline description
				throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
			}
			StringBuilder eventName = new StringBuilder();
			StringBuilder eventDate = new StringBuilder();
			String currentWord;
			Boolean reachedDatePortion = false;
			while(inLineScanner.hasNext()) {
				currentWord = inLineScanner.next();
				if (currentWord.equals("/at")) {
					//set switch to true but do not append
					reachedDatePortion = true;
				} else if (reachedDatePortion == false) {
					eventName.append(currentWord);
					eventName.append(" ");
				} else {
					eventDate.append(currentWord);
					eventDate.append(" ");
				}
			}
			if (eventDate.length() == 0) {
				throw new DukeException("no date provided");
			}
			taskArrayList.add(new Event(eventName.toString().trim(), eventDate.toString().trim()));
			alertLatestTaskAdded(taskArrayList);
		} else {
			//command not found
			throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
		}
    }

    private static void alertLatestTaskAdded(ArrayList<Task> taskArrayList) {
		System.out.println("Got it. I've added this task: ");
		System.out.println(taskArrayList.get(taskArrayList.size() -1));
		System.out.printf("Now you have %d tasks in the list.\n", taskArrayList.size());
	}

    private static void printTaskList(ArrayList<Task> taskArrayList) {
    	for (int i = 0; i < taskArrayList.size(); i++) {
    		System.out.printf("%d.%s\n", i + 1, taskArrayList.get(i).toString());
		}
	}
}

