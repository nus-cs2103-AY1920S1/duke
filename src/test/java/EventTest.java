import org.junit.jupiter.api.Test;
import task.Event;
import task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @Test
    public void eventTest() {
        Task event = new Event("Event Test","Some Event");
        assertEquals("Event Test", event.getDescription());
        assertEquals("E | 0 | Event Test | Some Event", event.fileFormat());
        assertEquals("[x]", event.getStatusIcon());
        assertEquals("[E][x] Event Test (at: Some Event)", event.toString());

        event.markAsDone();

        assertEquals("E | 1 | Event Test | Some Event", event.fileFormat());
        assertEquals("[done]", event.getStatusIcon());
        assertEquals("[E][done] Event Test (at: Some Event)", event.toString());
    }
}
