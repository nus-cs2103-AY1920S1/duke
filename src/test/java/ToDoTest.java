import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.task.ToDo;

public class ToDoTest {
    @Test
    public void testToFile() {
        assertEquals("T|N|test", new ToDo("test").toFile());
    }
}
