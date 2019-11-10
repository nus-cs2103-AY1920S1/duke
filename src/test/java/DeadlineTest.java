import org.junit.jupiter.api.Test;
import tasks.Deadline;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void testDescriptionGetter() {
        assertEquals("Test | 12/12/2000 0800", new Deadline("Test", "12/12/2000 0800").getDescription());
    }

    @Test
    void testStringConversion() {
        assertEquals("[D][0] Test (by: 12/12/2000 0800)",
                new Deadline("Test", "12/12/2000 0800").toString());
    }
}