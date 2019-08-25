package seedu.duke;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DeadlineTest {
    @Test
    public void test1() {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
            Date date = dateFormat.parse("24/08/2019");
            Date time = timeFormat.parse("1800");
            Deadline dl = new Deadline("abc", date, time);
            assertEquals("[D][\u2718] abc (by: 24/08/2019 18.00 PM)", dl.toString());
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
            assertEquals("[D][\u2713] abc (by: 24/08/2019 18.00 PM)", dl.toString());
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
