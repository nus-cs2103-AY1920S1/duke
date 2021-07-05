package seedu.duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoTest {
    int asciiTick = 0x2713;
    int asciiCross = 0x2718;
    String tick = Character.toString((char)asciiTick);
    String cross = Character.toString((char) asciiCross);

    @Test
    public void test1() {
        Todo td = new Todo("abc");
        assertEquals("[T][" + cross + "] abc", td.toString());
    }

    @Test
    public void test2() {
        Todo td = new Todo("abc");
        td.markAsDone();
        assertEquals("[T][" + tick + "] abc", td.toString());
    }

    @Test
    public void test3() {
        Todo td = new Todo("abc");
        assertEquals("T | 0 | abc", td.toWriteIntoFile());
    }

    @Test
    public void test4() {
        Todo td = new Todo("abc");
        td.markAsDone();
        assertEquals("T | 1 | abc", td.toWriteIntoFile());
    }
}
