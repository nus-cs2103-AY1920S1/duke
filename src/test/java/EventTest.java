import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void reprTest() {
        Event event = new Event("project meeting /at 6/8/2019 2015");
        try {
            assertEquals("[E][✘] project meeting (at: Tue Aug 06 20:15:00 SGT 2019)", event.repr());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
