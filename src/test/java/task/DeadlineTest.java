package task;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testToString() throws Exception {
        SimpleDateFormat dateWithTime = new SimpleDateFormat("dd/MM/yyy HHmm");
        Date date1 = dateWithTime.parse("12/11/2019 1300");
        Deadline deadline1 = new Deadline("submit project 1", date1);
        Deadline deadline2 = new Deadline("submit project 2", date1, true);
        assertEquals("[D][\u2718] submit project 1 (by: Tue 12/11/2019 1300)",
                deadline1.toString());
        assertEquals("[D][\u2713] submit project 2 (by: Tue 12/11/2019 1300)",
                deadline2.toString());
    }
}
