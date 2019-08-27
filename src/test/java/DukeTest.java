import duke.task.Deadline;
import duke.task.Todo;

import duke.command.Storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DukeTest {

    @Test
    public void testStorageGetFilePath() {
        assertEquals("/Users/auxin/duke/src/main",
                new Storage("/Users/auxin/duke/src/main").getFilePath());
    }

    @Test
    public void testDeadline() {
        assertEquals("D - \u2718 - Assignment - 30/8/2019 2359",
                new Deadline("Assignment", "30/8/2019 2359").toString());
    }

    @Test
    public void testTodo() {
        assertEquals("T - \u2718 - Reading", new Todo("Reading").toString());
    }
}
