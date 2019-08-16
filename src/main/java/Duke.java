import java.util.Scanner;

public class Duke {
	public enum Command {
		TODO, DEADLINE, EVENT, DONE, LIST, BYE;
		
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		TaskList taskList = new TaskList();
		String logo = " ____        _        \n"
			+ "|  _ \\ _   _| | _____ \n"
			+ "| | | | | | | |/ / _ \\\n"
			+ "| |_| | |_| |   <  __/\n"
			+ "|____/ \\__,_|_|\\_\\___|\n";
		String lineBreak = "    ___________________________________________________________\n";
		String greetingMessage = logo 
			+ lineBreak
			+ "     Hello! I'm Duke\n"
			+ "     What can I do for you?\n"
			+ lineBreak;
		String exitMessage = lineBreak
			+ "     Bye. Hope to see you again soon!\n"
			+ lineBreak;
		boolean takingInput = true;

		// print greeting message
		System.out.println(greetingMessage);

		// store console input as string
		String input = scanner.nextLine();

		// if input is not the exit command ("bye") process the input
		while (takingInput) {
			String[] inputSplit = input.split(" ", 2);
			Command command = null;
			String[] inputParameters = null;
			try {
				inputParameters = inputSplit[1].split("/by|/at", 2);
			} catch (Exception e) {
				// inputSplit[1] may not exist, ignore for now
			}

			switch(inputSplit[0]) {
				case "todo":
					command = Command.TODO; 
					break;
				case "deadline":
					command = Command.DEADLINE;
					break;
				case "event":
					command = Command.EVENT;
					break;
				case "list":
					command = Command.LIST;
					break;
				case "bye":
					command = Command.BYE;
					break;
				case "done":
					command = Command.DONE;
					break;
				default:
					break;
			}

			switch(command) {
				case BYE:
					System.out.println(exitMessage);
					takingInput = false;
					break;
				case LIST:
					int count = 0;
					System.out.print(lineBreak
							+ "     Here are the tasks in your list:\n");
					for (Task task : taskList) {
						count++;
						System.out.println("     " + count + ". " + task);
					}
					System.out.println(lineBreak);
					break;
				case DONE:
					Task currentTask = taskList.get(Integer.parseInt(inputParameters[0]) - 1);
					currentTask.complete();
					System.out.println(lineBreak
							+ "     Nice! I've marked this task as done:\n"
							+ "       " + currentTask + "\n"
							+ lineBreak);
					break;
				case TODO:
					taskList.add(new ToDo(inputParameters[0]));
					System.out.println(lineBreak
							+ "     Got it. I've added this task:\n"
							+ "       " + taskList.get(taskList.size() - 1) + "\n"
							+ "     Now you have " + taskList.size() + " tasks in the list.\n"
							+ lineBreak);
					break;
				case DEADLINE:
					taskList.add(new Deadline(inputParameters[0], inputParameters[1]));
					System.out.println(lineBreak
							+ "     Got it. I've added this task:\n"
							+ "       " + taskList.get(taskList.size() - 1) + "\n"
							+ "     Now you have " + taskList.size() + " tasks in the list.\n"
							+ lineBreak);
					break;
				case EVENT:
					taskList.add(new Event(inputParameters[0], inputParameters[1]));
					System.out.println(lineBreak
							+ "     Got it. I've added this task:\n"
							+ "       " + taskList.get(taskList.size() - 1) + "\n"
							+ "     Now you have " + taskList.size() + " tasks in the list.\n"
							+ lineBreak);
					break;
				default:
					break;
			}
			if (takingInput) {
				input = scanner.nextLine();
			}
		}
	}
}
