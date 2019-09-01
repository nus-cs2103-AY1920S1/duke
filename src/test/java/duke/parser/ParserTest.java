package duke.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_invalidCommand_unknownCommandExceptionThrown() {
        try {
            Parser.parse("test");
            fail();
        } catch (Exception e) {
            assertEquals(" \u2639 OOPS!!!  I'm sorry, but I don't know what that means :-(", e.toString());
        }
    }
}
