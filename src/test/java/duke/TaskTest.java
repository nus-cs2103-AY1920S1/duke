package duke;

import duke.tasks.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    String expectedToFile = "T | 0 | Ride a bike";
    String expectedToString = "[T][✓] Slept well";


    @Test
    public void testToString() {
        Task task = new Task(" Slept well", true);
        assertEquals(expectedToString, task.toString());
    }

    @Test
    public void setExpectedToFile() {
        Task task = new Task(" Ride a bike", false);
        assertEquals(expectedToFile, task.toFile());
    }


}
