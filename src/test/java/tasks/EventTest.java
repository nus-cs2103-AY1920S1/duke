package tasks;

import customexceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testStringConversion() throws DukeException {
        assertEquals("E|-|test|20/10/2019 2100", new Event("test","20/10/2019 2100").toString());
    }
}
