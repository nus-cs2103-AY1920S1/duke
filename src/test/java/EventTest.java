import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    private final String TICK = "\u2713";
    private final String CROSS = "\u2717";

    @Test
    public void instanceTest() {
        // Test for Date, Time and Both
        try {   
            new Event("This is a Event.", "01/01/2001 19:02");
        } catch (DukeException e) {
            fail();
        }

        try {
            new Event("This is a Event.", "01/01/2001");
        } catch (DukeException e) {
            fail();
        }

        try {
            new Event("This is a Event.", "19:02");
        } catch (DukeException e) {
            fail();
        }
        
        // Test for wrong time, date or both
        try {
            new Event("This is a Event.", "99/99/9999 99:99");
            fail();
        } catch (DukeException e) {
            // Pass
        }

        try {
            new Event("This is a Event.", "99/99/9999");
            fail();
        } catch (DukeException e) {
            // Pass
        }

        try {
            new Event("This is a Event.", "99:99");
            fail();
        } catch (DukeException e) {
            // Pass
        }

        try {
            new Event("19:02.", "Fail");
            fail();
        } catch (DukeException e) {
            // Pass
        }
        
    }

    @Test
    public void toStringTest() {
        Event event;
        try {
            event = new Event("This is a Event.", "14/05/2020");
        } catch (DukeException e) {
            event = null;
            fail();
        }
        assertEquals("[E][" + CROSS + "] This is a Event. (at: 14/05/2020)", 
                event.toString());
        event.setCompleted(true);    
        assertEquals("[E][" + TICK + "] This is a Event. (at: 14/05/2020)", 
                event.toString());
    }


}