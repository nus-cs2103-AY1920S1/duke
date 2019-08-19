import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
		ArrayList<Task> taskArrayList = new ArrayList<Task>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
		System.out.println("What can I do for you?");

        String userInput = scanner.nextLine();
        Scanner inLineScanner = new Scanner(userInput);
        String command = inLineScanner.next();

        while (!command.equals("bye")) {
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
			} else {
        		switch(command) {
					case "todo":
						taskArrayList.add(new ToDo(inLineScanner.nextLine().trim()));
						break;
					case "deadline":
						String[] deadlineNameAndDate = inLineScanner.nextLine().split("/by");
						String deadLineName = deadlineNameAndDate[0].trim();
						String deadLineDate = deadlineNameAndDate[1].trim();
						taskArrayList.add(new DeadLine(deadLineName, deadLineDate));
						break;
					case "event":
						String[] eventNameAndDate = inLineScanner.nextLine().split("/at");
						String eventName = eventNameAndDate[0].trim();
						String eventDate = eventNameAndDate[1].trim();
						taskArrayList.add(new Event(eventName, eventDate));
						break;
				}
        		System.out.println("Got it. I've added this task: ");
				System.out.println(taskArrayList.get(taskArrayList.size() -1));
				System.out.printf("Now you have %d tasks in the list.\n", taskArrayList.size());
			}
        	userInput = scanner.nextLine();
			inLineScanner = new Scanner(userInput);
			command = inLineScanner.next();
		}
        System.out.print("Bye. Hope to see you again soon!");
    }

    private static void printTaskList(ArrayList<Task> taskArrayList) {
    	for (int i = 0; i < taskArrayList.size(); i++) {
    		System.out.printf("%d.%s\n", i + 1, taskArrayList.get(i).toString());
		}
	}
}

class Task {
	String taskName;
	boolean doneStatus;

	public Task(String taskName) {
		this.taskName = taskName;
		this.doneStatus = false;
	}

	public void markCompleted() {
		this.doneStatus = true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (doneStatus == true) {
			sb.append("[✓]");
		} else {
			sb.append("[✗]");
		}
		sb.append(" ");
		sb.append(taskName);
		return sb.toString();
	}
}

class ToDo extends Task {
	public ToDo(String taskName) {
		super(taskName);
	}

	@Override
	public String toString() {
		return "[T]" + super.toString();
	}
}

class DeadLine extends Task {
	private String endDate;

	public DeadLine(String taskName, String endDate) {
		super(taskName);
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "[D]" + super.toString() + " (by: " + endDate + ")";
	}
}

class Event extends Task {
	private String eventDate;

	public Event(String taskName, String eventDate) {
		super(taskName);
		this.eventDate = eventDate;
	}

	@Override
	public String toString() {
		return "[E]" + super.toString() + " (at: " + eventDate + ")";
	}
}

//class DukeException extends Exception {
//	public DukeException
//}
