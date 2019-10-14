
import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.exception.InsufficientArgument;
import duke.exception.InvalidCommand;
import duke.exception.InvalidDescription;
import duke.exception.MissingPreposition;
import org.junit.jupiter.api.Test;

public class ExceptionTest {

    @Test
    public void testInsufficientArgument() {
        assertEquals("OOPS!!! I need more than this.", new InsufficientArgument().toString());
    }

    @Test
    public void testInvalidCommand() {
        assertEquals("OOPS!!! I'm sorry, but I don't know what that means.", new InvalidCommand().toString());
    }

    @Test
    public void testInvalidDescription() {
        assertEquals("OOPS!!! The description of a test cannot be empty.", new InvalidDescription("test").toString());
    }

    @Test
    public void testMissingPreposition() {
        assertEquals("Oops!! test requires a preposition for the time.", new MissingPreposition("test").toString());
    }
}
