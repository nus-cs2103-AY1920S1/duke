package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    @Test
    void testEventCreation() throws DukeException {
        //CHECKSTYLE.OFF: AvoidEscapedUnicodeCharactersCheck
        assertEquals("[E][\u2718] Rest (at: 1 Sep 2019 00:00)", new Event("Rest", "01/09/2019 0000").toString());
        //CHECKSTYLE.ON: AvoidEscapedUnicodeCharactersCheck
    }

    @Test
    void testEventDataString() throws DukeException {
        assertEquals("E | 0 | Rest | 01/09/2019 0000", new Event("Rest", "01/09/2019 0000").toDataString());
    }
}
