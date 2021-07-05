package seedu.duke;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    int asciiTick = 0x2713;
    int asciiCross = 0x2718;
    String tick = Character.toString((char)asciiTick);
    String cross = Character.toString((char) asciiCross);

    @Test
    public void test1() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
            Date date = dateFormat.parse("24/08/2019");
            Date time = timeFormat.parse("1800");
            Deadline dl = new Deadline("abc", date, time);
            assertEquals("[D][" + cross + "] abc (by: 24 Aug 2019 6.00 PM)", dl.toString());
        } catch (Exception e) {
            System.out.println("failed test");
        }
    }

    @Test
    public void test2() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
            Date date = dateFormat.parse("24/08/2019");
            Date time = timeFormat.parse("1800");
            Deadline dl = new Deadline("abc", date, time);
            dl.markAsDone();
            assertEquals("[D][" + tick + "] abc (by: 24 Aug 2019 6.00 PM)", dl.toString());
        } catch (Exception e) {
            System.out.println("failed test");
        }
    }

    @Test
    public void test3() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
            Date date = dateFormat.parse("24/08/2019");
            Date time = timeFormat.parse("1800");
            Deadline dl = new Deadline("abc", date, time);
            assertEquals("D | 0 | abc | 24/08/2019 1800", dl.toWriteIntoFile());
        } catch (Exception e) {
            System.out.println("failed test");
        }
    }

    @Test
    public void test4() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
            Date date = dateFormat.parse("24/08/2019");
            Date time = timeFormat.parse("1800");
            Deadline dl = new Deadline("abc", date, time);
            dl.markAsDone();
            assertEquals("D | 1 | abc | 24/08/2019 1800", dl.toWriteIntoFile());
        } catch (Exception e) {
            System.out.println("failed test");
        }
    }
}
