import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
    	Task[] taskArray = new Task[100];
    	int taskCounter = 0;
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
        		String prettifiedList = listConverter(taskArray);
        		System.out.println(prettifiedList);
			} else if (command.equals("done")) {
        		Integer taskNumber = Integer.parseInt(inLineScanner.next()) - 1;
				String output = taskArray[taskNumber].markCompleted();
				System.out.println(output);
			} else {
        		switch(command) {
					case "todo":
						taskArray[taskCounter] = new ToDo(inLineScanner.nextLine().trim());
						break;
					case "deadline":
						String[] deadlineNameAndDate = inLineScanner.nextLine().split("/by");
						String deadLineName = deadlineNameAndDate[0].trim();
						String deadLineDate = deadlineNameAndDate[1].trim();
						taskArray[taskCounter] = new DeadLine(deadLineName, deadLineDate);
						break;
					case "event":
						String[] eventNameAndDate = inLineScanner.nextLine().split("/at");
						String eventName = eventNameAndDate[0].trim();
						String eventDate = eventNameAndDate[1].trim();
						taskArray[taskCounter] = new Event(eventName, eventDate);
						break;
				}
        		taskCounter++;
        		System.out.println("Got it. I've added this task: ");
				System.out.println(taskArray[taskCounter - 1]);
				System.out.printf("Now you have %d tasks in the list.\n", taskCounter);
			}
        	userInput = scanner.nextLine();
			inLineScanner = new Scanner(userInput);
			command = inLineScanner.next();
		}

        System.out.print("Bye. Hope to see you again soon!");
    }

    private static String[] getTaskNameAndDate(Scanner inLineScanner, String dateIndicator) {
    	String currentWord = inLineScanner.next();
    	StringBuilder taskName  = new StringBuilder();
    	StringBuilder date = new StringBuilder();
    	String[] taskNameAndDate = new String[2];
    	while (!currentWord.equals("dateIndicator")) {
			taskName.append(currentWord);
			currentWord = inLineScanner.next();
		}
    	while(inLineScanner.hasNext()) {
			date.append(currentWord);
			currentWord = inLineScanner.next();
		}
    	taskNameAndDate[0] = taskName.toString();
    	taskNameAndDate[1] = date.toString();
    	return taskNameAndDate;
	}

    private static String listConverter(Task[] list) {
    	StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.length; i++)
		{
			if (list[i] != null) {
				sb.append(i + 1 + "." + list[i].toString());
				sb.append("\n");
			}
		}
		return sb.toString().trim();
	}
}

class Task {
	String taskName;
	boolean doneStatus;

	public Task(String taskName) {
		this.taskName = taskName;
		this.doneStatus = false;
	}

	public String markCompleted() {
		this.doneStatus = true;
		StringBuilder sb = new StringBuilder("Nice! I've marked this task as done:\n");
		sb.append("    ");
		sb.append("[✓]");
		sb.append(" ");
		sb.append(taskName);
		return sb.toString();
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
