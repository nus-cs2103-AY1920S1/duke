package seedu.duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void test1() {
        ToDo thing = new ToDo("many many things");
        assertEquals("many many things", thing.description);
    }

    @Test
    public void test2() {
        ToDo thing = new ToDo("1234567890!@#$%^&*()qwertyuiop");
        assertEquals("1234567890!@#$%^&*()qwertyuiop", thing.description);
    }
}
