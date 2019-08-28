import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void doneEventTest() {
        Event dummyEvent = new Event("dummy", "02/10/2019 2000", false);
        dummyEvent.setTaskAsDone(true);
        assertEquals(true, dummyEvent.isDone());
    }

    @Test
    public void parseDateTest() {
        assertEquals(true, new Event("dummy", "02/10/2019 2000", false));
    }
}