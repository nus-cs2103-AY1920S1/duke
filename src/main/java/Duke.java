import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.Function;

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

    public static void main(String[] args) {
		//Initialize input.
		Scanner s = new Scanner(System.in);
		ArrayList<String> storageList = new ArrayList<>();

		//Start off greeting the user.
		say(initialGreeting);

		//While there is still input from user
inputLoop:
		while(s.hasNextLine()) {
			//Read single line of user input, and remove extra spaces
			String userInput = s.nextLine().trim();

			switch(userInput) {
			case "bye": 
				say("Bye. Hope to see you again soon!");
				break inputLoop;
			case "list":
				say(storageList.stream().map(new CounterDecorator(1)).iterator());
				break;
			default:
				say("added: " + userInput);
				storageList.add(userInput);
				break;
			}
		}
    }
}
