package duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void toString_normalDeadline_convertedCorrectly() {
        assertEquals("[T][" + '\u2718' + "] Quiz 1", new ToDo("Quiz 1").toString());
    }


    @Test
    public void toStore_normalDeadline_convertedCorrectly() {
        assertEquals("T|0|Quiz 1", new ToDo("Quiz 1").toStore());
    }

}
