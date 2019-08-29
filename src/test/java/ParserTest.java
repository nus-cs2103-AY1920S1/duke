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
        assertEquals(parser.parseInteger("1"), 1);
    }

    @Test
    public void parseEventTest() throws DukeException {
        Parser parser = new Parser();
        String taskStr = "concert /at 2019-08-31 21:00";
        assertEquals("[E][✗] concert (at: 2019-08-31 21:00)", parser.parseEvent(taskStr).toString());
    }
}
