import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseCommandTest() {
        String todoInput = "todo bake eggs";
        String deadlineInput = "deadline return book /by 2019-08-31 18:00";
        String eventInput = "event concert /at 2019-08-31 21:00";
        Parser parser = new Parser();
        assertEquals(parser.parseCommand(todoInput), "todo");
        assertEquals(parser.parseCommand(deadlineInput), "deadline");
        assertEquals(parser.parseCommand(eventInput), "event");
    }

    @Test
    public void parseIntegerTest() throws DukeException {
        Parser parser = new Parser();
        assertEquals(parser.parseInteger("done 1"), 1);
        assertEquals(parser.parseInteger("delete 2"), 2);
    }

    @Test
    public void parseEventTest() throws DukeException {
        Parser parser = new Parser();
        String input = "event concert /at 2019-08-31 21:00";
        assertEquals(String.format("[E][%s] concert (at: 2019-08-31 21:00)", Unicode.CROSS),
                parser.parseEvent(input).toString());
    }
}
