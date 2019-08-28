import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void testGetCommandType() {
        assertEquals("todo", new Parser("todo read book")
                .getCommandType());
    }

    @Test
    public void testStringConversion() {
        assertEquals("[T][✗]read book", new Todo("read book",
                false).toString());
    }

}