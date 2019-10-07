import duke.Command;
import duke.Parser;
import duke.TodoCommand;
import duke.exception.InvalidTaskException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parse_normalTodoCommandInput_returnTodoCommand() {
        try {
            Parser parser = new Parser();
            Command testCommand = parser.parse("todo make this test");
            Command perfectCommand = new TodoCommand("make this test");
            assertEquals(testCommand.getType(), perfectCommand.getType());
            assertEquals(((TodoCommand) testCommand).getDescription(), ((TodoCommand) perfectCommand).getDescription());
        } catch (InvalidTaskException e) {
            fail("normalTodoCommandInput threw exception: " + e.getMessage());
        }
    }

    @Test
    public void parse_noDescriptionTodoCommandInput_throwInvalidTaskException() {
        try {
            Parser parser = new Parser();
            parser.parse("todo");
        } catch (InvalidTaskException e) {
            return;
        }
        fail("noDescriptionTodoCommand created without exception thrown");
    }
}