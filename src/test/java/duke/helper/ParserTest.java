package duke.helper;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_invalidInput_exceptionThrown() {
        try {
            Parser.parse("Random gibberish");
            fail();
        } catch (DukeException de) {
            String correctExpected = ":( OOPS!!! I'm sorry, but I don't know what that means :-(";
            assertEquals(correctExpected, de.getMessage());
        }
    }
}
