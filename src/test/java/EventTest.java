import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void getAtDateTimeString() {
        Event event;
        try {
            event = new Event("borrow book", "28/09/2019 19:00");
            assertEquals("28/09/2019 19:00", event.getAtDateTimeString());
        } catch (DukeException e) {

        }
    }

    @Test
    void toSaveString() {
        Event event;
        try {
            event = new Event("borrow book", "28/09/2019 19:00");
            assertEquals("EVENT@@@false@@@borrow book@@@28/09/2019 19:00", event.toSaveString());
        } catch (DukeException e) {

        }
    }

    @Test
    void testToString() {
        Event event;
        try {
            event = new Event("borrow book", "28/09/2019 19:00");
            assertEquals("[E][\u2718] borrow book (at: 28/09/2019 19:00)", event.toString());
        } catch (DukeException e) {

        }
    }
}