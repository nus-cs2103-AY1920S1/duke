import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    public Deadline constructDeadline() throws Exception {
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
        Date date = parser.parse("2/12/2019");
        LocalTime time = LocalTime.of(23, 59);
        Deadline d = new Deadline("test", date, time);
        return d;
    }

    @Test
    public void testDateStringConversion() throws Exception {
        Deadline d = constructDeadline();
        assertEquals("02-Dec-2019", d.getDateString());
    }

    @Test
    public void testTimeString() throws Exception {
        Deadline d = constructDeadline();
        assertEquals("11:59PM", d.getTimeString());
    }

    @Test
    public void testStringConversion() throws Exception {
        Deadline d = constructDeadline();
        assertEquals("[D][✘] test (by: 02-Dec-2019 11:59PM)", d.toString());
    }
}
