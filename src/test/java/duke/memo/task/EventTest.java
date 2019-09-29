package duke.memo.task;

import duke.memo.record.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EventTest {

    @Test
    void eventTest() throws Throwable {
        Event event = new Event("have meeting /at 1/9/2019 1400");
        assertEquals("[E]✘ have meeting (at: 01/09/2019 1400)", event.toString());
        event.taskDone();
        assertEquals("[E]✓ have meeting (at: 01/09/2019 1400)", event.toString());
    }

    @Test
    void eventGetPrintableMsgTest() throws Throwable {
        Event event = new Event("have meeting /at 2/9/2019 2359");
        assertEquals("E | 0 | have meeting | 02/09/2019 2359", event.getPrintableMsg());
        event.taskDone();
        assertEquals("E | 1 | have meeting | 02/09/2019 2359", event.getPrintableMsg());
    }
}