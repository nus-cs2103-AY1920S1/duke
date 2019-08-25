package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

	@Test
	public void parse_bye() throws DukeException {
		assertEquals(new ExitCommand(), Parser.parse("bye"));
	}

	@Test
	public void parse_list() throws DukeException {
		assertEquals(new ListCommand(), Parser.parse("list"));
	}

	@Test
	public void parse_delete() throws DukeException {
		assertEquals(new DeleteCommand(2), Parser.parse("delete 2"));
	}

	@Test
	public void parse_done() throws DukeException {
		assertEquals(new DoneCommand(2), Parser.parse("done 2"));
	}

	@Test
	public void parse_todo_noExceptionThrown() throws DukeException {
		assertEquals(new AddTodoCommand("read book"), Parser.parse("todo read book"));
	}

	@Test
	public void parse_todo_exceptionThrown() throws DukeException {
		try {
			Parser.parse("todo    ");
			fail();
		} catch (DukeException exception) {
			assertEquals("\u2639 OOPS!!! The description of a todo cannot be empty.", exception.getMessage());
		}
	}

	@Test
	public void parse_deadline() throws DukeException {
		assertEquals(new AddDeadlineCommand("homework /by 06/06/2019"),
				Parser.parse("deadline homework /by 06/06/2019"));
	}

	@Test
	public void parse_event() throws DukeException {
		assertEquals(new AddEventCommand("project meeting /at 06/06/2019 1800 /to 06/06/2019 1900"),
				Parser.parse("event project meeting /at 06/06/2019 1800 /to 06/06/2019 1900"));
	}

	@Test
	public void parse_invalidCommand() throws DukeException {
		try {
			Parser.parse("hey");
			fail();
		} catch (DukeException exception) {
			assertEquals("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(", exception.getMessage());
		}
	}

}
