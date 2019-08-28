package duke.command;

import duke.task.Event;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    @Test
    public void eventToFileFormat_validInput_success() {
        try {
            Event event = new Event("read book", "2/12/2019 1800");
            assertEquals("E|0|read book|2/12/2019 1800", event.format());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}