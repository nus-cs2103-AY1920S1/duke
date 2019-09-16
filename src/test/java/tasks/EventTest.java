package tasks;

import org.junit.jupiter.api.Test;

import utils.DateTime;

import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    private Event event; 

    public EventTest() throws ParseException {
        this.event = new Event("concert", new DateTime("01-01-2019 15:34"));
    }

    @Test
    void storageStringTest() {
        assertEquals("E | false | concert | 01-01-2019 15:34", event.getStorageString());
    }
    
    @Test
    void listStringTest() {
        assertEquals("[E][✘] concert (at: 01-01-2019 15:34)", event.toString());
    }
}