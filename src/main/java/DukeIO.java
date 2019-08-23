import java.util.Arrays;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;

public class DukeIO {

	private static final String lineRule =
		"____________________________________________________________";

	private final Map<String, Predicate<String>> commandMap
		= new HashMap<>();
	private Predicate<String> defaultHandler = null;

	private final Scanner parser;

	public DukeIO() {
		this.parser = new Scanner(System.in);
	}

	public void say(String... lines) {
		say(Arrays.asList(lines).iterator());
	}
	public void say(Iterator<String> lines) {
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

	public void bindCommand(String command, Predicate<String> handler) {
		this.commandMap.put(command, handler);
	}

	public void setUnknownCommandHandler(Predicate<String> handler) {
		this.defaultHandler = handler;
	}

	public void listen() {
		//While there is still input from user
		while(parser.hasNextLine()) {
			//Read single line of user input, and remove extra spaces
			String userInput = parser.nextLine().trim();

			int spacePos = userInput.indexOf(' ');
			String command, rest;
			if(spacePos == -1) {
				command = userInput;
				rest = null;
			} else {
				command = userInput.substring(0, spacePos);
				rest = userInput.substring(spacePos+1);
			}

			Predicate<String> cmdHandler = commandMap.get(command);
			boolean shouldExit;
			if(cmdHandler != null) {
				shouldExit = cmdHandler.test(rest);
			} else {
				shouldExit = defaultHandler.test(userInput);
			}

			if(shouldExit) {
				break;
			}
		}
	}

}
