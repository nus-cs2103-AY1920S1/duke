import duke.exception.InvalidCommandException;
import duke.exception.MissingDescriptionException;
import duke.exception.MissingInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void executeCommand_InvalidCommand_ExceptionThrown() {
        try {
            Parser parser = new Parser();
            parser.executeCommand("listtt");
            fail();
        } catch (InvalidCommandException e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        } catch (MissingDescriptionException | MissingInputException e) {
            fail();
            e.printStackTrace();
        }
    }
}
