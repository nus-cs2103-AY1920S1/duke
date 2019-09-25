package duke;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    void parse_byeCommand() throws DukeException {
        assertEquals(new ExitCommand(), Parser.parse("bye"));
    }

    @Test
    void parse_listCommand() throws DukeException {
        assertEquals(new ListCommand(), Parser.parse("list"));
    }

    @Test
    void parse_deleteCommand() throws DukeException {
        assertEquals(new DeleteCommand(1), Parser.parse("delete 1"));
    }

    @Test
    void parse_doneCommand() throws DukeException {
        assertEquals(new DoneCommand(1), Parser.parse("done 1"));
    }

    @Test
    void parse_todoCommand() throws DukeException {
        assertEquals(new AddCommand(new Todo("read book")), Parser.parse("todo read book"));
    }
}
