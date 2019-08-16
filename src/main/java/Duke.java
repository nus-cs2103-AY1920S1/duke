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
		String exitCommand = "bye";
		System.out.println(
				logo 
				+ lineBreak
				+ "     Hello! I'm Duke\n"
				+ "     What can I do for you?\n"
				+ lineBreak);
		String command = scanner.next();
		while (!command.equals(exitCommand)) {
			System.out.println(
					lineBreak
					+ "     "
					+ command
					+ "\n"
					+ lineBreak);
			command = scanner.next();
		}
		System.out.println(
				lineBreak
				+ "     Bye. Hope to see you again soon!\n"
				+ lineBreak);
	}
}
