import tasks.ToDo;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {

    @Test
    public void testToStringFormat() {
        assertEquals("[T][\u2718] take Japanese 3",
                new ToDo("take Japanese 3",
                        false).toString());
    }

    @Test
    public void testFileStringFormat() {
        assertEquals("T | 1 | start attending crossfit classes",
                new ToDo("start attending crossfit classes",
                        true).fileString());
    }
}