import java.util.Arrays;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class DukeIO {

	private static final String lineRule =
		"____________________________________________________________";

	private final Map<String, Predicate<Command>> commandMap
		= new HashMap<>();
	private Predicate<Command> defaultHandler = null;
	private boolean printingDialogBlock;

	private final Scanner scanner;

	public DukeIO() {
		this.scanner = new Scanner(System.in);
	}

	public void say(String... lines) {
		say(Arrays.asList(lines).iterator());
	}
	public void say(Iterator<String> lines) {
		if(!this.printingDialogBlock) {
			//Print start of reply line
			System.out.println(lineRule);
			this.printingDialogBlock = true;
		}

		//Print each given line
		while(lines.hasNext()) {
			System.out.println(lines.next());
		}
	}

	public void bindCommand(String command, Predicate<Command> handler) {
		this.commandMap.put(command, handler);
	}

	public void setUnknownCommandHandler(Predicate<Command> handler) {
		this.defaultHandler = handler;
	}

	public void withDialogBlock(Runnable action) {
		this.printingDialogBlock = false;
		try {
			action.run();
		} finally {
			if(this.printingDialogBlock) {
				//Print end of reply line, and extra empty line
				System.out.println(lineRule);
				System.out.println();
			}
		}
	}
	public <T> T withDialogBlock(Supplier<T> action) {
		this.printingDialogBlock = false;
		try {
			return action.get();
		} finally {
			if(this.printingDialogBlock) {
				//Print end of reply line, and extra empty line
				System.out.println(lineRule);
				System.out.println();
			}
		}
	}

	public void listen() {
		//While there is still input from user
		while(scanner.hasNextLine()) {
			//Read single line of user input, and remove extra spaces
			String userInput = scanner.nextLine();

			Command command = Command.parse(userInput);

			Predicate<Command> cmdHandler = commandMap.get(command.type);
			boolean shouldExit;
			if(cmdHandler != null) {
				shouldExit = this.withDialogBlock(() -> cmdHandler.test(command));
			} else if(defaultHandler != null) {
				shouldExit = this.withDialogBlock(() -> defaultHandler.test(command));
			} else {
				this.withDialogBlock(
						() -> say(String.format("Unknown command %s", command.type))
						);
				shouldExit = false;
			}

			if(shouldExit) {
				break;
			}
		}
	}

}
