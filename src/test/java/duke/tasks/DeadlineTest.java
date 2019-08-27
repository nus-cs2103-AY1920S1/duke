package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][✘] Quiz 1 (by: tomorrow)", new Deadline("Quiz 1", "tomorrow").toString());
    }


    @Test
    public void testStoringConversion() {
        assertEquals("D|0|Quiz 1|tomorrow", new Deadline("Quiz 1", "tomorrow").toStore());
    }

}
