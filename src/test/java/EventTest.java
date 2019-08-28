
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    @Test
    void dummyTest() {
        Event test = new Event("Why","E","18/09/2109 1406");
        String testType = test.getType();
        assertEquals("E", testType);
        String testStatus = test.getStatus();
        assertEquals("\u2718", testStatus);
        test.markDone();
        testStatus = test.getStatus();
        assertEquals("\u2713", testStatus);
        String info = test.getTaskInfo();
        assertEquals("Why", info);
        String by = test.getBy();
        assertEquals("Wed Sep 18 14:06:00 SGT 2109", by);

    }
    static void main(String[] args) {
    }
}