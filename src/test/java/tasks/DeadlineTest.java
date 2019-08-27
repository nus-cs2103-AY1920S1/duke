package tasks;

import customexceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringConversion() throws DukeException {
        assertEquals("D|-|test|20/10/2019 2100", new Deadline("test","20/10/2019 2100").toString());
    }
}
