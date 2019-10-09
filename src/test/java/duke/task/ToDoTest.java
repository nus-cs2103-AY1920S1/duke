package duke.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTest {
    @Test
    void testToDoCreation() {
        //CHECKSTYLE.OFF: AvoidEscapedUnicodeCharactersCheck
        assertEquals("[T][\u2718] Rest", new ToDo("Rest").toString());
        //CHECKSTYLE.ON: AvoidEscapedUnicodeCharactersCheck
    }

    @Test
    void testToDoDataString() {
        assertEquals("T | 0 | Rest", new ToDo("Rest").toDataString());
    }
}
