package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void event_initialise_success() throws DukeException {
        Event event = new Event("test event",
                LocalDateTime.parse("2019-07-06T18:00"),
                LocalDateTime.parse("2019-07-06T20:00"));
        assertEquals(LocalDateTime.parse("2019-07-06T18:00"), event.getStartDate());
        assertEquals(LocalDateTime.parse("2019-07-06T20:00"), event.getEndDate());
        event.setStartDate(LocalDateTime.parse("2019-08-06T20:00"));
        event.setEndDate(LocalDateTime.parse("2019-09-06T20:00"));
        assertEquals(LocalDateTime.parse("2019-08-06T20:00"), event.getStartDate());
        assertEquals(LocalDateTime.parse("2019-09-06T20:00"), event.getEndDate());
        assertEquals("[E][X] test event (at: 6 Aug 19 20:00 to 6 Sep 19 20:00)", event.toString());
    }
}
