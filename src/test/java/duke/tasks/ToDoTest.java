package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void testStringConversion() {
        assertEquals("[T][✘] Quiz 1", new ToDo("Quiz 1").toString());
    }


    @Test
    public void testStoringConversion() {
        assertEquals("T|0|Quiz 1", new ToDo("Quiz 1").toStore());
    }

}
