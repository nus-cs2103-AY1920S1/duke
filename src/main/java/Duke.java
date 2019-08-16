import java.util.Scanner;

public class Duke {

	private static final String[] initialGreeting = new String[]{
		"Hello! I'm Duke",
		"What can I do for you?"
	};

	private static final String lineRule =
		"____________________________________________________________";

	private static void say(String... lines) {
		System.out.println(lineRule);
		for(String line : lines) {
			System.out.println(line);
		}
		System.out.println(lineRule);
		System.out.println();
	}

    public static void main(String[] args) {
		say(initialGreeting);
		Scanner s = new Scanner(System.in);
		while(s.hasNextLine()) {
			String userInput = s.nextLine();
			if("bye".equals(userInput.trim())) {
				say("Bye. Hope to see you again soon!");
				break;
			}
			say(userInput);
		}
    }
}
