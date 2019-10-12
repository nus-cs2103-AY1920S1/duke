import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class to test the {@link FileReading} class
 */

public class FileReadingTest {

    /**
     * Utilise {@link TaskListModifierTest} to create a 'duke'.txt file
     * and then read the file and compare data.
     */

    @Test
    public void fileReadTest() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>(100);
        TaskListModifierTest modifyTaskListTest = new TaskListModifierTest();
        modifyTaskListTest.modifyTest();
        FileReading.checkFileExists(taskList);
        String expectedOutput1 = "[T][✘] finish JUnit testing";
        String expectedOutput2 = "[D][✓] finish Project (by: Sat Apr 20 10:00:00 SGT 2019)";
        String expectedOutput3 = "[E][✘] game event (at: Wed Dec 23 02:30:00 SGT 2020)";
        assertEquals(expectedOutput1, taskList.get(0).toString());
        assertEquals(expectedOutput2, taskList.get(1).toString());
        assertEquals(expectedOutput3, taskList.get(2).toString());
    }
}
