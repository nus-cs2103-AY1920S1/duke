 import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParserTest {

    @Test
    public void parseTest() throws EmptyTaskDukeException, InvalidTaskDukeException, InvalidInputDukeException {
        assertEquals(Parser.parse("list"), new PrintListCommand());
        assertEquals(Parser.parse("delete 1"), new DeleteCommand(1));
    }
}
