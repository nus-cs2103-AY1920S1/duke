package dude.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testStringConversion() {
        assertEquals("[+] task String test", new Task("task String test", 1).toString());
    }
}
