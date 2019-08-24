package duke.task;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testToSaveString() throws ParseException {
        String description = "hackathon";
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy HHmm");
        Date at = dateFormat.parse("21/9/2019 0930");
        Event event = new Event(description, at);
        assertEquals("E | 0 | hackathon | 21/9/2019 0930",event.toSaveString());
    }

    @Test
    public void testToString() throws ParseException {
        String description = "hackathon";
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy HHmm");
        Date at = dateFormat.parse("21/9/2019 0930");
        Event event = new Event(description, at);
        assertEquals("[E][\u2718] hackathon (at: 21/9/2019 0930)",event.toString());
    }

}