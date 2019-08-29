import duke.exception.DukeException;
import duke.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class TodoTest {

    @Test
    public void getAscii_normalDescription_success() throws DukeException {
        assertEquals("T | 0 | abc", (new Todo("abc")).getAscii());
        assertEquals("T | 0 | return book", (new Todo("return book")).getAscii());
        assertEquals("T | 0 | buy book", (new Todo("buy book")).getAscii());
    }

    @Test
    public void constructor_blankDescription_exception() {
        try {
            assertEquals("", new Todo(" "));
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a Todo cannot be empty.",
                    e.getMessage());
        }
    }
}
