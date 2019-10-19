package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    void setUp() {
        taskList = new TaskList();
    }

    void addTask() {
        taskList.addTask(new TaskImplStub("Test"));
    }

    @Test
    void getTasks() {
        assertEquals(new ArrayList<Task>(), taskList.getTasks());
    }

    @Test
    void getTaskByIndex_invalidTaskId_exceptionThrown() {
        try {
            taskList.getTaskByIndex(1);
            fail();
        } catch (DukeException e) {
            assertEquals("Task No.1 is not present in your list. "
                    + "Please enter a valid task ID.", e.getMessage());
        }
    }

    @Test
    void addTask_increaseSize() {
        assertEquals(0, taskList.getTasks().size());
        addTask();
        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    void deleteTaskByIndex_validTaskId_success() throws DukeException {
        addTask();
        assertEquals(1, taskList.getTasks().size());
        taskList.deleteTaskByIndex(1);
        assertEquals(0, taskList.getTasks().size());
    }

    @Test
    void deleteTaskByIndex_invalidTaskId_exceptionThrow() {
        try {
            taskList.deleteTaskByIndex(1);
            fail();
        } catch (DukeException e) {
            assertEquals("Task No.1 is not present in your list. "
                    + "Please enter a valid task ID.", e.getMessage());
        }
    }

    @Test
    void getSize() {
        assertEquals(0, taskList.getSize());
        addTask();
        assertEquals(1, taskList.getSize());
    }

    @Test
    void isEmpty() {
        assertTrue(taskList.isEmpty());
        addTask();
        assertFalse(taskList.isEmpty());
    }
}
