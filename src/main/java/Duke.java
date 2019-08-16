import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String logo = " ____        _        \n"
			+ "|  _ \\ _   _| | _____ \n"
			+ "| | | | | | | |/ / _ \\\n"
			+ "| |_| | |_| |   <  __/\n"
			+ "|____/ \\__,_|_|\\_\\___|\n";
		String lineBreak = "    ___________________________________________________________\n";
		final String exitCommand = "bye";
		String greetingMessage = logo 
			+ lineBreak
			+ "     Hello! I'm Duke\n"
			+ "     What can I do for you?\n"
			+ lineBreak;
		String exitMessage = lineBreak
			+ "     Bye. Hope to see you again soon!\n"
			+ lineBreak;
		boolean takingInput = true;
		List<String> taskList = new ArrayList<String>();

		// print greeting message
		System.out.println(greetingMessage);

		// store console input as string
		String command = scanner.nextLine();
		String output = "";

		// if input is not the exit command ("bye") process the input
		while (takingInput) {
			switch(command) {
				case exitCommand:
					System.out.println(exitMessage);
					takingInput = false;
					break;
				case "list":
					int count = 0;
					System.out.print(lineBreak);
					for (String task : taskList) {
						count++;
						System.out.println("     " + count + ". " + task);
					}
					System.out.println(lineBreak);
					break;
				default:
					System.out.println(lineBreak
							+ "     added: "
							+ command
							+ "\n"
							+ lineBreak);
					taskList.add(command);
					break;
			}
			if (takingInput) {
				command = scanner.nextLine();
			}
		}
	}
}
