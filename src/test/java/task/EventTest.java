package task;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void testToString() throws Exception {
        SimpleDateFormat dateWithTime = new SimpleDateFormat("dd/MM/yyy HHmm");
        Date date1 = dateWithTime.parse("12/11/2019 1300");
        Event event1 = new Event("attend wedding 1", date1);
        Event event2 = new Event("attend wedding 2", date1, true);
        assertEquals("[E][\u2718] attend wedding 1 (at: Tue 12/11/2019 1300)",
                event1.toString());
        assertEquals("[E][\u2713] attend wedding 2 (at: Tue 12/11/2019 1300)",
                event2.toString());
    }
}
