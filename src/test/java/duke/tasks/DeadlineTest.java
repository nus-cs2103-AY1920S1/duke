package duke.tasks;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void toString_normalDeadline_convertedCorrectly() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = cal.getTime();
        assertEquals("[D][" + '\u2718' + "] Quiz 1\n(by: "
                + new SimpleDateFormat("EEE, d MMM yyyy, hh:mm a").format(tomorrow) + ")",
                new Deadline("Quiz 1", tomorrow).toString());
    }

    @Test
    public void toStore_normalDeadline_convertedCorrectly() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = cal.getTime();
        assertEquals("D|0|Quiz 1|" + new SimpleDateFormat("EEE, d MMM yyyy, hh:mm a").format(tomorrow),
                new Deadline("Quiz 1", tomorrow).toStore());
    }

}
