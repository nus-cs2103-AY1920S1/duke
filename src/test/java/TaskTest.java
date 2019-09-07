import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testCreateTaskInFileFormat() {
        Task testObject = new Task("eat apple") {
            @Override
            public void editDateAndTime(String newDateAndTime) {

            }
        };
        String testObjectString = testObject.createTaskInFileFormat();
        assertEquals("0 eat apple", testObjectString);
    }
}
