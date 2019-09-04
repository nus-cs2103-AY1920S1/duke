import duke.time.Date;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateTest {
    @Test
    public void DateTest() {
        try {
            Date date = new Date(1, 2, 1987);
            assertTrue(date.toString().equals("1st of February 1987"));
            assertTrue(date.isValid());
        } catch (DukeException error) {
            System.out.println(error);
        }
    }

}
