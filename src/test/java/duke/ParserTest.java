package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.function.DoubleUnaryOperator;

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
    public void parse_todo() throws DukeException {
        assertEquals(new AddTodoCommand("read book"), Parser.parse("todo read book"));
    }

    @Test
    public void parse_deadline() throws DukeException {
        assertEquals(new AddDeadlineCommand("read book /by 2/12/2019 1800"), Parser.parse("deadline read book /by 2/12/2019 1800"));
    }

    @Test
    public void parse_event() throws DukeException {
        assertEquals(new AddEventCommand("book reading /at 2/12/2019 1800 /to 2/12/2019 1900"),
                Parser.parse("event book reading /at 2/12/2019 1800 /to 2/12/2019 1900"));
    }

    @Test
    public void parse_done() throws DukeException {
        assertEquals(new DoneCommand(1), Parser.parse("done 2"));
    }

    @Test
    public void parse_delete() throws DukeException {
        assertEquals(new DeleteCommand(1), Parser.parse("delete 2"));
    }

    @Test
    public void parse_error() throws DukeException {
        try {
            Parser.parse("blah");
            fail();
        } catch (Exception e) {
            assertEquals("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
