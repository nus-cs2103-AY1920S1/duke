package task;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    @Test
    void dateFormat() throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("11/12/2222 18:00");
        Deadline deadline = new Deadline("testingDate", false, date);

        assertEquals("11-12-2222 18:00", deadline.stringDate());
    }
}
