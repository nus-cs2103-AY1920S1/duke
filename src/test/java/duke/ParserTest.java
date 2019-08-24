package duke;

import duke.command.ExitCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parseTest() {
        try {
            assertTrue(Parser.parse("bye") instanceof ExitCommand);
            assertThrows(DukeException.class, () -> Parser.parse("byesth"));
        } catch (DukeException e) {
            e.printStackTrace();
        }
    }
}
