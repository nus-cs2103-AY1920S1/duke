import org.junit.jupiter.api.Test;
import task.Event;
import tool.DateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testDoneStorageString() {
        DateTime dt = new DateTime("18/10/1999 1800");
        Event e = new Event("read book", dt);
        e.markAsDone();
        String res = e.storageString();
        assertEquals("E/1/read book/18 October 1999 1800hrs", res);
    }
}
