package dude.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testStringConversion() {
        assertEquals("[E] [+] event String test (at: 20 Oct 2019, 4-6pm)",
                new Event("event String test", 1, "20 Oct 2019, 4-6pm").toString());
    }
}
