package weomucat.duke;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
	@Test
	public void commandShouldBeEmptyString() {
		String[] tests = {"", " ", "  ", "   "};

		for (String test : tests) {
			Parser parser = new Parser(test);
			String command = parser.getCommand();

			assertEquals("", command, formatMessage(test));
		}
	}

	@Test
	public void commandShouldBeOneWord() {
		String[] tests = {
				"todo", " todo ", "  todo  ", "   todo   ",
				"todo one", " todo one ", "  todo  one  ", "   todo   one   "
		};

		for (String test : tests) {
			Parser parser = new Parser(test);
			String command = parser.getCommand();

			assertEquals("todo", command, formatMessage(test));
		}
	}

	@Test
	public void parametersShouldBeEmptyString() {
		String[] tests = {
				"event", " event ", "  event  ", "   event   ",
				"event /from /to", " event /from /to ", "  event  /from  /to  ", "   event   /from   /to   "
		};

		for (String test : tests) {
			Parser parser = new Parser(test);
			String command = parser.getCommand();
			HashMap<String, String> parameters = parser.parseParameters("/from", "/to");

			assertEquals("event", command, formatMessage(test));
			assertEquals("", parser.getBody(), formatMessage(test));
			assertEquals("", parameters.get("/from"), formatMessage(test));
			assertEquals("", parameters.get("/to"), formatMessage(test));
		}
	}

	@Test
	public void parametersShouldBeOneWord() {
		String[] tests = {
				"todo one /from two /to three", " todo one /from two /to three ", "  todo  one  /from  two  /to  three  ", "   todo   one   /from   two   /to   three   "
		};

		for (String test : tests) {
			Parser parser = new Parser(test);
			String command = parser.getCommand();
			HashMap<String, String> parameters = parser.parseParameters("/from", "/to");

			assertEquals("todo", command, formatMessage(test));
			assertEquals("one", parser.getBody(), formatMessage(test));
			assertEquals("two", parameters.get("/from"), formatMessage(test));
			assertEquals("three", parameters.get("/to"), formatMessage(test));
		}
	}

	@Test
	public void parametersShouldBeWords() {
		String[] tests = {
				"todo one two three /from four five six /to seven eight nine",
				" todo one two three /from four five six /to seven eight nine ",
				"  todo  one two three  /from  four five six  /to  seven eight nine  ",
				"   todo   one two three   /from   four five six   /to   seven eight nine   "
		};

		for (String test : tests) {
			Parser parser = new Parser(test);
			String command = parser.getCommand();
			HashMap<String, String> parameters = parser.parseParameters("/from", "/to");

			assertEquals("todo", command, formatMessage(test));
			assertEquals("one two three", parser.getBody(), formatMessage(test));
			assertEquals("four five six", parameters.get("/from"), formatMessage(test));
			assertEquals("seven eight nine", parameters.get("/to"), formatMessage(test));
		}
	}

	@Test
	public void parametersShouldPreserveSpaces() {
		String[] tests = {
				"todo one   two   three /from four   five   six /to seven   eight   nine",
				" todo one   two   three /from four   five   six /to seven   eight   nine ",
				"  todo  one   two   three  /from  four   five   six  /to  seven   eight   nine  ",
				"   todo   one   two   three   /from   four   five   six   /to   seven   eight   nine   "
		};

		for (String test : tests) {
			Parser parser = new Parser(test);
			String command = parser.getCommand();
			HashMap<String, String> parameters = parser.parseParameters("/from", "/to");

			assertEquals("todo", command, formatMessage(test));
			assertEquals("one   two   three", parser.getBody(), formatMessage(test));
			assertEquals("four   five   six", parameters.get("/from"), formatMessage(test));
			assertEquals("seven   eight   nine", parameters.get("/to"), formatMessage(test));
		}
	}

	@Test
	public void parametersOrderDoesNotMatter() {
		String[] tests = {
				"todo one two /a three four /b five six /c seven eight",
				"todo one two /b five six /c seven eight /a three four",
				"todo one two /c seven eight /a three four /b five six",
		};

		for (String test : tests) {
			Parser parser = new Parser(test);
			String command = parser.getCommand();
			HashMap<String, String> parameters = parser.parseParameters("/a", "/b", "/c");

			assertEquals("todo", command, formatMessage(test));
			assertEquals("one two", parser.getBody(), formatMessage(test));
			assertEquals("three four", parameters.get("/a"), formatMessage(test));
			assertEquals("five six", parameters.get("/b"), formatMessage(test));
			assertEquals("seven eight", parameters.get("/c"), formatMessage(test));
		}
	}

	private String formatMessage(String input) {
		return String.format("Input: '%s'", input);
	}
}
