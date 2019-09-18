import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void testParseForList() throws DukeException {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

    @Test
    public void testParseForDelete() throws DukeException {
        assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
    }

    @Test
    public void testParseForDone() throws DukeException {
        assertTrue(Parser.parse("done 1") instanceof DoneCommand);
    }

    @Test
    public void testParseForExit() throws DukeException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    public void testParseForAdd() throws DukeException {
        assertTrue(Parser.parse("todo buy lunch") instanceof AddCommand);
        assertTrue(Parser.parse("deadline return books /by 02/12/2019 1900") instanceof AddCommand);
        assertTrue(Parser.parse("event team meeting /at 02/12/2019 1900-2000") instanceof AddCommand);
    }
}
