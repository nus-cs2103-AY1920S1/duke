import java.util.Scanner;

public class Duke {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
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

			switch(inputSplit[0]) {
				case "bye":
					System.out.println(exitMessage);
					takingInput = false;
					break;
				case "list":
					System.out.print(lineBreak);
					for (Task task : Task.taskList) {
						System.out.println("     " + task);
					}
					System.out.println(lineBreak);
					break;
				case "done":
					Task currentTask = Task.taskList.get(Integer.parseInt(inputSplit[1]) - 1);
					currentTask.complete();
					System.out.println(lineBreak
							+ "     Nice! I've marked this task as done:\n"
							+ "       " + currentTask.toString().split("[.]", 2)[1] + "\n"
							+ lineBreak);
					break;
				default:
					System.out.println(lineBreak
							+ "     added: "
							+ input
							+ "\n"
							+ lineBreak);
					Task.createTask(input);
					break;
			}
			if (takingInput) {
				input = scanner.nextLine();
			}
		}
	}
}
