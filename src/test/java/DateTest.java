import org.junit.jupiter.api.Test;
import duke.time.Date;
import duke.exception.DukeException;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DateTest {
    @Test
    public void DateTest() {
        try {
            Date date = new Date(1, 2, 1999);
            assertEquals("1st of February 1999", date.toString());
            assertEquals(true, date.isValid());
        } catch (DukeException error) {
            System.out.println("     Caught!");
        }

    }
}
