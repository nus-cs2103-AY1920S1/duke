import main.DateTime;
import main.DukeException;
import task.Deadlines;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testDateTime(){
        try {
            assertEquals("10/10/2001 1000", new DateTime("10/10/2001 1000").toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testDeadlines(){
        try {
            assertEquals("[D][\u2713] test (by: 10/10/1000 1000)", new Deadlines("test",
                    new DateTime("10/10/1000 1000"), true).toString());
        } catch (DukeException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testDukeException(){
        assertEquals("hi", new DukeException("hi").getMessage());
    }
}
