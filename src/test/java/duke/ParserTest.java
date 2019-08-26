package duke;

import duke.command.AddCommand;
import duke.command.Command;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParseTodo() {
        try {
            Command command = Parser.parse("todo write report");
            assertEquals(new AddCommand("todo", "write report"), command);
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testParseDeadline() {
        try {
            Command command = Parser.parse("deadline finish report /by 28/8/2019");
            assertEquals(new AddCommand("deadline", "finish report", "by", "28/8/2019"), command);
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testParseEvent() {
        try {
            Command command = Parser.parse("event project meeting /at 26/8/2019");
            assertEquals(new AddCommand("event", "project meeting", "at", "26/8/2019"), command);
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }
}
