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
        String userInput = scanner.nextLine();
        String[] splitUserInput = userInput.split(" ");
        String command = splitUserInput[0];

        while (!command.equals("bye")) {
        	if (userInput.equals("list")) {
        		String prettifiedList = listConverter(taskArray);
        		System.out.println(prettifiedList);
			} else if (command.equals("done")) {
        		Integer taskNumber = Integer.parseInt(splitUserInput[1]);
				String output = taskArray[taskNumber].markCompleted();
				System.out.println(output);
			} else {
        		taskArray[taskCounter] = new Task(userInput);
        		taskCounter++;
				System.out.println("added: " + userInput);
			}
        	userInput = scanner.nextLine();
			splitUserInput = userInput.split(" ");
			command = splitUserInput[0];
		}
        System.out.print("Bye. Hope to see you again soon!");
    }

    private static String listConverter(Task[] list) {
    	StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.length; i++)
		{
			if (list[i] != null) {
				sb.append(i + 1 + ". " + list[i].toString());
				sb.append("\n");
			}
		}
		return sb.toString().trim();
	}
}

class Task
{
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
