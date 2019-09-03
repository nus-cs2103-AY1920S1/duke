import duke.data.DateTime;
import duke.task.Deadline;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateTimeTest {
    @Test
    public void testDateTime() throws ParseException {
        String date = "2/9/2019 1200";
        Deadline dl = new Deadline("Buy Gift", date);
        DateTime dateTime = new DateTime(date);
        assertEquals(dl.getDeadline(), dateTime.getDateTimeString());
    }
}
