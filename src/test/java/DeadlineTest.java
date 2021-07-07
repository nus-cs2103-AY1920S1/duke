import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void getBy() {
        assertEquals("2nd of December 2019, 6pm",new Deadline("homework","2nd of December 2019, 6pm").getBy());
    }

    @Test
    void testToString() {
        assertEquals("[D]✘ homework (by: 2nd of December 2019, 6pm)",new Deadline("homework","2nd of December 2019, 6pm").toString());
    }
}