import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void printString_todo_expectedBehaviour() {
        Todo task = new Todo("print lecture notes");
        assertEquals("T | 0 | print lecture notes", task.printToTxtFile());
    }

    @Test
    public void printString_deadline_expectedBehaviour() {
        try {
            Deadline task = new Deadline("complete lab 1", "7/9/2019 2359");
            assertEquals("D | 0 | complete lab 1 | 07/09/2019 2359", task.printToTxtFile());

        } catch (Exception e) {
            //shouldn't happen
        }
    }

    @Test
    public void printString_event_expectedBehaviour() {
        try {
            Event task = new Event("attend formal dinner", "4/9/2019 1900");
            assertEquals("E | 0 | attend formal dinner | 04/09/2019 1900", task.printToTxtFile());

        } catch (Exception e) {
            //shouldn't happen
        }
    }
}
