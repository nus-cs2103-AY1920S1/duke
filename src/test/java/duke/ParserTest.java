package duke;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import duke.command.ToDoCommand;

class MyFirstJUnitJupiterTests {
    @Test
    void parse_parseTodo_none() {
        try {
            assertTrue(Parser.parse("todo eat") instanceof ToDoCommand);
        } catch (Exception e) {
            // fail silently
        }
    }
}
