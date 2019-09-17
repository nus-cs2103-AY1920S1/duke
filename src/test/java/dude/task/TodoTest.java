package dude.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T] [  ] todo String test", new Todo("todo String test", 0).toString());
    }
}
