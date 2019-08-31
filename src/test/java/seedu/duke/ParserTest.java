import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.duke.Parser;
import seedu.duke.exception.DukeException;

public class ParserTest {
    @Test
    public void getTypeTest() throws DukeException {
        assertEquals(new Parser("bye").getType(), "bye");
    }

    @Test
    public void getDescriptionTest() throws DukeException {
        assertEquals(new Parser("todo test").getDescription(), "test");
    }

    @Test
    public void getIndexTest() throws DukeException {
        assertEquals(new Parser("delete 1").getIndex(), 1);
    }

}
