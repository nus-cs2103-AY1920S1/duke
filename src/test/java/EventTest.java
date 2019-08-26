import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    public Event constructEvent() throws Exception {
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
        Date date = parser.parse("8/9/2019");
        LocalTime time = LocalTime.of(00, 00);
        Event e = new Event("test", date, time);
        return e;
    }

    @Test
    public void testDateStringConversion() throws Exception {
        Event e = constructEvent();
        assertEquals("08-Sep-2019", e.getDateString());
    }

    @Test
    public void testTimeString() throws Exception {
        Event e = constructEvent();
        assertEquals("12:00AM", e.getTimeString());
    }

    @Test
    public void testStringConversion() throws Exception {
        Event e = constructEvent();
        assertEquals("[E][✘] test (at: 08-Sep-2019 12:00AM)", e.toString());
    }
}
