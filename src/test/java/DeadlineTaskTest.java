import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTaskTest {
    @Test
    public void toString_completedTask_tickText() {
        assertEquals("[D][✗] homework (By: 12 December 2019, 6PM)",
                new DeadlineTask("homework /by 12/12/2019 18:00").toString());
    }

    @Test
    public void toString_invalidInput_exception() throws Exception {
        try {
            assertEquals(0, new DeadlineTask("hello world"));
            fail();
        } catch (Exception e) {
            assertEquals("OOPS!!! The description /by of a deadline task cannot be empty.",e.toString());
        }
    }

    @Test
    public void toString_invalidDate_exception() throws Exception {
        try {
            assertEquals(0, new DeadlineTask("hello world /by :)"));
            fail();
        } catch (Exception e) {
            assertEquals("Sorry, Dates should be in format 'dd/mm/yyyy hh:mm'",e.toString());
        }
    }
}
