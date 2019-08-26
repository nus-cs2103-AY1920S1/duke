package duke.task;

import duke.exception.TaskException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskFactoryTest {
    @Test
    public void test_newToDo() {
        try {
            String description = "todo n1";
            Task t = TaskFactory.create(description);
            assertEquals("[T][✗] n1", t.toString());
        } catch (TaskException e) {
            fail();
        }
    }

    @Test
    public void test_newDeadline() {
        try {
            String description = "deadline n2 /by 1/1/2017 0800";
            Task t = TaskFactory.create(description);
            assertEquals("[D][✗] n2 (by: 1st of January 2017, 8:00am)", t.toString());
        } catch (TaskException e) {
            fail();
        }
    }

    @Test
    public void test_newEvent() {
        try {
            String description = "event n3 /at 5/5/2018 2359";
            Task t = TaskFactory.create(description);
            assertEquals("[E][✗] n3 (at: 5th of May 2018, 11:59pm)", t.toString());
        } catch (TaskException e) {
            fail();
        }
    }


}
