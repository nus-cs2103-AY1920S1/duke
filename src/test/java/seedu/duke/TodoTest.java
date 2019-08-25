package seedu.duke;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TodoTest {
    @Test
    public void test1() {
        Todo td = new Todo("abc");
        assertEquals("[T][\u2718] abc", td.toString());
    }
}
