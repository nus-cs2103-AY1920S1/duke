import duke.task.ToDoTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTaskTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][Not done] Say hi", new ToDoTask("Say hi").toString());
    }
}
