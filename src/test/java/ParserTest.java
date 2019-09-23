import SerSnapsalot.command.HelpCommand;
import SerSnapsalot.exception.DukeException;
import SerSnapsalot.parser.Parser;
import org.junit.jupiter.api.Test;

public class ParserTest {

	@Test
	void parserTest() {
		Parser parser = new Parser();
		String testsPassed = "";
		String[] keywords = {"find", "clear", "bye", "save", "archive", "list", "delete",
								"done", "event", "deadline", "todo", "snap", "help"};

		for (String fullCommand : keywords) {
			try {
				if (fullCommand.contains("find")) {
					parser.parseFind(fullCommand);
					testsPassed += "1";
				} else if (fullCommand.equals("clear")) {
					parser.parseClear(fullCommand);
					 testsPassed += "2";
				} else if (fullCommand.equals("bye")) {
					parser.parseExit(fullCommand);
					testsPassed += "3";
				} else if (fullCommand.equals("save")) {
					parser.parseSave(fullCommand);
					testsPassed += "4";
				} else if (fullCommand.contains("archive")) {
					parser.parseArchive(fullCommand);
					testsPassed += "5";
				} else if (fullCommand.equals("list")) {
					parser.parseList(fullCommand);
					testsPassed += "6";
				} else if (fullCommand.contains("delete")) {
					parser.parseDelete(fullCommand);
					testsPassed += "7";
				} else if (fullCommand.contains("done")) {
					parser.parseMarkDone(fullCommand);
					testsPassed += "8";
				} else if (fullCommand.contains("event")) {
					parser.parseAddCommand(fullCommand);
					testsPassed += "9";
				} else if (fullCommand.contains("deadline")) {
					parser.parseAddCommand(fullCommand);
					testsPassed += "10";
				} else if (fullCommand.contains("todo")) {
					parser.parseAddCommand(fullCommand);
					testsPassed += "11";
				} else if (fullCommand.equals("snap")) {
					parser.parseSnapCommand("snap");
					testsPassed += "12";
				} else {
					new HelpCommand("help", fullCommand);
					testsPassed += "13";
				}
			} catch (DukeException e) {
				//Do nothing to exceptions.
			}
		}
		//Only tests 2, 3, 4, 6, 12, 13 should pass,
		//as they do not require additional specifiers to keywords.
		assert (testsPassed.equals("23461213"));

		System.out.println("---- Test: Parser commands are running.");
	}
}
