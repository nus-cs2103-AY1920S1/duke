package duke.parser;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_invalidCommands_unrecognizedCommandError() {
        try {
            Parser p = new Parser();
            assertEquals("Unrecognized Command! Please type 'help' for details.", p.parseInput("hello"));
            assertEquals("Unrecognized Command! Please type 'help' for details.", p.parseInput("whats up"));
            assertEquals("Unrecognized Command! Please type 'help' for details.", p.parseInput("igloo"));
        } catch (DukeException e) {
            assertEquals("Unrecognized Command! Please type 'help' for details.", e.getMessage());
        }
    }

}
