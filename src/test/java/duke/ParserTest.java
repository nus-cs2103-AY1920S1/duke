import duke.*;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void parse_deadlineCommand() throws DukeException {
        Deadline expectedDeadline = new Deadline("return book");
        try {
            expectedDeadline.parseTime("06/06/2019 1800");
        } catch (ParseException ex) {
            throw new DukeException(ex.getMessage());
        }
        assertEquals(new AddCommand(expectedDeadline), Parser.parse("deadline return book /by 06/06/2019 1800"));
    }

    @Test
    void parse_eventCommand() throws DukeException {
        Event expectedEvent = new Event("project meeting");
        try {
            expectedEvent.parseTime("2/12/2019 1400");
        } catch (ParseException ex) {
            throw new DukeException(ex.getMessage());
        }
        assertEquals(new AddCommand(expectedEvent), Parser.parse("event project meeting /at 2/12/2019 1400"));
    }

}
