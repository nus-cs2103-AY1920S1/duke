package duke;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import duke.command.ToDoCommand;

class ParserTest {
    @Test
    void parseToDoCommand() {
        try {
            assertTrue(Parser.parse("todo eat") instanceof ToDoCommand);
        } catch (Exception e) {
            // fail silently
        }
    }
}
