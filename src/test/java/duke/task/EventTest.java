package duke.task;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    @Test
    void eventTestWithString(){
        Event test = new Event("event", "eventTime");
        assertEquals("[E][✘] event (at: eventTime)", test.toString(), "toString() method works");

        test.markAsDone();
        assertEquals("[E][✓] event (at: eventTime)", test.toString(), "markAsDone() method works");
    }

    @Test
    void eventTestWithDate(){
        Event test = new Event("event", new Date(0));
        assertEquals("[E][✘] event (at: 01/01/1970 07:30:00)", test.toString(), "toString() method works");

        test.markAsDone();
        assertEquals("[E][✓] event (at: 01/01/1970 07:30:00)", test.toString(), "markAsDone() method works");
    }
}
