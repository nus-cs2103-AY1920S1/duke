package duke.lib;

import duke.lib.common.DukeException;
import duke.lib.datahandling.TaskList;
import duke.lib.task.Task;
import duke.lib.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskListTest {

    @Test
    public void testGetSize() throws DukeException {
        TaskList taskListUnderTest = new TaskList();
        assertEquals(0, taskListUnderTest.getSize());
        Task todo = new ToDo("eat");
        taskListUnderTest.store(todo);
        assertEquals(1, taskListUnderTest.getSize());
    }

    @Test
    public void testMarkAsDone() throws DukeException {
        TaskList taskListUnderTest = new TaskList();
        try {
            taskListUnderTest.markAsDone(1);
        } catch (DukeException expected) {
            assertEquals("There's no Task attached to that number", expected.getMessage());
        }
        Task todo = new ToDo("eat");
        taskListUnderTest.store(todo);
        assertEquals(todo, taskListUnderTest.markAsDone(1));
    }
}