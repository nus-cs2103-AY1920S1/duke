import org.junit.jupiter.api.Test;
import tasks.Event;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    Event testEvent = new Event("party", "11/11/2011 1800 2200");
    @Test
    public void getDateTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate testDate = LocalDate.parse("11/11/2011", formatter);
        LocalDateTime testDateTime = testDate.atTime(18,00);
        testEvent.getDate();
        assertEquals(testDateTime, testEvent.getDateTime());
    }

    @Test
    public void getExtraInfoTest() {
        assertEquals(testEvent.getExtraInfo(), "11/11/2011 1800 2200");
    }
}
