package duke;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskImplStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    void addTask() {
        taskList.add(new TaskImplStub("Test"));
    }

    @Test
    void getTasks() {
        assertEquals(new ArrayList<Task>(), taskList.getTasks());
    }

    @Test
    void getTask_invalidTaskId_exceptionThrown() {
        try {
            taskList.get(1);
            fail();
        } catch (DukeException e) {
            assertEquals("Task No.1 is not present in your list. "
                    + "Please enter a valid task ID.", e.getMessage());
        }
    }

    @Test
    void add() {
        assertEquals(0, taskList.getTasks().size());
        addTask();
        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    void remove_validTaskId_success() throws DukeException {
        addTask();
        assertEquals(1, taskList.getTasks().size());
        taskList.remove(1);
        assertEquals(0, taskList.getTasks().size());
    }

    @Test
    void remove_invalidTaskId_exceptionThrow() {
        try {
            taskList.remove(1);
            fail();
        } catch (DukeException e) {
            assertEquals("Task No.1 is not present in your list. "
                    + "Please enter a valid task ID.", e.getMessage());
        }
    }

    @Test
    void size() {
        assertEquals(0, taskList.size());
        addTask();
        assertEquals(1, taskList.size());
    }

    @Test
    void isEmpty() {
        assertEquals(true, taskList.isEmpty());
        addTask();
        assertEquals(false, taskList.isEmpty());
    }
}
