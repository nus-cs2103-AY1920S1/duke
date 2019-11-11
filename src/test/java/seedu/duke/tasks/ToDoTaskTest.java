package seedu.duke.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ToDoTaskTest {
    @Test
    public void toString_format() {
        String description = "example toDoTask";
        ToDoTask toDoTask = new ToDoTask(description);

        assertTrue(toDoTask.toString().matches("^\\[\\d{5}\\]\\[T\\]\\[[OX]\\] .*$"));

        String stringValue = toDoTask.toString().split("] ", 2)[1];
        assertEquals(stringValue, description);
    }

    @Test
    public void toString_status() {
        String description = "example toDoTask";
        ToDoTask toDoTask = new ToDoTask(description);

        String isDoneStr = toDoTask.toString().split("]\\[", 3)[2].substring(0, 1);
        assertEquals(isDoneStr, "X");

        toDoTask.markAsDone();
        isDoneStr = toDoTask.toString().split("]\\[", 3)[2].substring(0, 1);
        assertEquals(isDoneStr, "O");
    }
}
