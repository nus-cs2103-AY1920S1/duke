package seedu.duke;


import org.junit.jupiter.api.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
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
            Event event = new Event("abc", date, time);
            assertEquals("[E][" + cross + "] abc (at: 24 Aug 2019 6.00 PM)", event.toString());
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
            Event event = new Event("abc", date, time);
            event.markAsDone();
            assertEquals("[E][" + tick + "] abc (at: 24 Aug 2019 6.00 PM)", event.toString());
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
            Event dl = new Event("abc", date, time);
            assertEquals("E | 0 | abc | 24/08/2019 1800", dl.toWriteIntoFile());
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
            Event dl = new Event("abc", date, time);
            dl.markAsDone();
            assertEquals("E | 1 | abc | 24/08/2019 1800", dl.toWriteIntoFile());
        } catch (Exception e) {
            System.out.println("failed test");
        }
    }
}
