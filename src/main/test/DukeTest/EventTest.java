package DukeTest;

import Task.Event;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void TestStringConversion() throws ParseException {
        assertEquals("[E][✘] run(at: Wed Dec 12 12:00:00 SGT 2012)", new Event("run", "12/12/2012 1200").toString());
    }

}