package myduke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import myduke.exception.DukeException;
import myduke.exception.DukeInvalidCommandException;
import myduke.task.TaskList;

import org.junit.jupiter.api.Test;

class TaskListTest {

    @Test
    void testTaskList_CreateAndDeleteSingleElement() throws DukeException {
        TaskList tasks = new TaskList();
        assertEquals(tasks.size(), 0);

        tasks.add(new TaskStub());
        assertEquals(tasks.size(), 1);

        //Index starts at 1
        assertThrows(DukeInvalidCommandException.class, () -> tasks.deleteTask(0));
        assertEquals(tasks.size(), 1);

        assertThrows(DukeInvalidCommandException.class, () -> tasks.deleteTask(-1));
        assertEquals(tasks.size(), 1);

        assertThrows(DukeInvalidCommandException.class, () -> tasks.deleteTask(2));
        assertEquals(tasks.size(), 1);

        tasks.deleteTask(1);
        assertEquals(tasks.size(), 0);

        assertThrows(DukeInvalidCommandException.class, () -> tasks.deleteTask(1));
        assertEquals(tasks.size(), 0);
    }

    @Test
    void testTaskList_AddTodo() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.add(new TaskStub());
        assertEquals(tasks.getTask(1).toString(), "Test Stub");

        assertThrows(DukeInvalidCommandException.class, () -> tasks.getTask(-1));
        assertThrows(DukeInvalidCommandException.class, () -> tasks.getTask(0));
        assertThrows(DukeInvalidCommandException.class, () -> tasks.getTask(2));
    }
}
