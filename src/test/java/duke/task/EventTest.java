package duke.task;

import duke.command.DukeException;
import duke.command.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    @Test
    void printStartTime() {
        try {
            Event event = new Event(new Parser("eVEnt lol /at 31/05/1969 1800-2359"));
            assertEquals("31st May, 1969. 6:00pm", event.printStartTime());
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    void printEndTime() {
        try {
            Event event = new Event(new Parser("event lol /at 31/05/1969 1800-2359"));
            assertEquals("31st May, 1969. 11:59pm", event.printEndTime());
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    void testToString() {
        try {
            assertEquals("[E][-] go to lecture (at: 13th February, 2019. 4:00am - 13th February, 2019. 8:00pm)",
                    new Event(new Parser("eveNT go to lecture /at 13/02/2019 0400-2000")).toString());
            Event event = new Event(new Parser("event /at 27/07/2000 1400"));
        } catch (DukeException e) {
            assertEquals(" :( OOPS!!! Invalid Format. Either Description or "
                    + "Date and/or start and end time not provided.", e.getMessage());
        }
    }
}