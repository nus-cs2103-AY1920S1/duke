package seedu.duke;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TodoTest {
    @Test
    public void test1() {
        Todo td = new Todo("abc");
        assertEquals("[T][\u2718] abc", td.toString());
    }

    @Test
    public void test2() {
        Todo td = new Todo("abc");
        td.markAsDone();
        assertEquals("[T][\u2713] abc", td.toString());
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
