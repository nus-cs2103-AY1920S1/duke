package duke.task;

import duke.task.exceptions.TaskException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test for TaskFactory Class.
 */
public class TaskFactoryTest {
    /**
     * Tests construction of a todo task.
     */
    @Test
    public void test_newToDo() {
        try {
            String description = "todo n1";
            Task t = TaskFactory.create(description);
            assertEquals("[T][N] n1", t.toString());
        } catch (TaskException e) {
            fail();
        }
    }

    /**
     * Tests construction of a deadline task.
     */
    @Test
    public void test_newDeadline() {
        try {
            String description = "deadline n2 /by 1/1/2017 0800";
            Task t = TaskFactory.create(description);
            assertEquals("[D][N] n2 (by: 1st of January 2017, 8:00am)", t.toString());
        } catch (TaskException e) {
            fail();
        }
    }

    /**
     * Tests construction of a event task.
     */
    @Test
    public void test_newEvent() {
        try {
            String description = "event n3 /at 5/5/2018 2359";
            Task t = TaskFactory.create(description);
            assertEquals("[E][N] n3 (at: 5th of May 2018, 11:59pm)", t.toString());
        } catch (TaskException e) {
            fail();
        }
    }


}
