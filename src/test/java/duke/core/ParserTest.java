package duke.core;

import duke.command.ExitCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parse_invalidFirstWord_exceptionThrown() {
        try {
            assertEquals(new ExitCommand(), Parser.parse("byo"));
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means...\n"
                            + "The first word of your instruction seems to be an invalid command :-(",
                    e.getMessage());
        }
    }
}
