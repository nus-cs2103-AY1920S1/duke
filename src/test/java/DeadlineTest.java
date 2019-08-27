import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    private final String TICK = "\u2713";
    private final String CROSS = "\u2717";

    @Test
    public void instanceTest() {
        // Test for Date, Time and Both
        try {   
            new Deadline("This is a Deadline.", "01/01/2001 19:02");
        } catch (DukeException e) {
            fail();
        }

        try {
            new Deadline("This is a Deadline.", "01/01/2001");
        } catch (DukeException e) {
            fail();
        }

        try {
            new Deadline("This is a Deadline.", "19:02");
        } catch (DukeException e) {
            fail();
        }
        
        // Test for wrong time, date or both
        try {
            new Deadline("This is a Deadline.", "99/99/9999 99:99");
            fail();
        } catch (DukeException e) {
            // Pass
        }

        try {
            new Deadline("This is a Deadline.", "99/99/9999");
            fail();
        } catch (DukeException e) {
            // Pass
        }

        try {
            new Deadline("This is a Deadline.", "99:99");
            fail();
        } catch (DukeException e) {
            // Pass
        }

        try {
            new Deadline("19:02.", "Fail");
            fail();
        } catch (DukeException e) {
            // Pass
        }
        
    }

    @Test
    public void toStringTest() {
        Deadline deadline;
        try {
            deadline = new Deadline("This is a Deadline.", "14/05/2020");
        } catch (DukeException e) {
            deadline = null;
            fail();
        }
        assertEquals("[D][" + CROSS + "] This is a Deadline. (by: 14/05/2020)", 
                deadline.toString());
        deadline.setCompleted(true);    
        assertEquals("[D][" + TICK + "] This is a Deadline. (by: 14/05/2020)", 
                deadline.toString());
    }


}