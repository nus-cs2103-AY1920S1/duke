package dude.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D] [  ] deadline String test (by: 20 Oct 2019, 06:00 PM)",
                new Deadline("deadline String test", 0, "20 Oct 2019, 06:00 PM").toString());
    }
}
