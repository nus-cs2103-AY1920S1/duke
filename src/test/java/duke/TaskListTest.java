package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.exception.DukeException;
import duke.exception.InvalidTaskDukeException;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

class TaskListTest {
    @Test
    void testTaskList_CreateOneElement() {
        TaskList tasks = new TaskList();
        tasks.addTask(new TaskStub());
        assertEquals(tasks.size(), 1);
    }

    @Test
    void testTaskList_CreateAndDeleteOneElement() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.addTask(new TaskStub());
        tasks.deleteTask(0);
        assertEquals(tasks.size(), 0);
    }

    @Test
    void testTaskList_DeleteInvalidElement() {
        TaskList tasks = new TaskList();
        assertThrows(InvalidTaskDukeException.class, () -> tasks.deleteTask(0));
    }

    @Test
    void testTaskList_NoElementAtStart()  {
        TaskList tasks = new TaskList();
        assertEquals(tasks.size(), 0);
    }

    @Test
    void testTaskList_AddTodo() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("Test 1"));
        assertEquals(tasks.getTask(0).getDescription(), "Test 1");
    }

    @Test
    void testTaskList_GetInvalidElement() {
        TaskList tasks = new TaskList();
        assertThrows(InvalidTaskDukeException.class, () -> tasks.getTask(0));
    }

    @Test
    void testTaskList_FindTasksByKeyword() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("hi"));
        tasks.addTask(new Todo("bye"));
        assertEquals(tasks.findTasksByKeyword("bye").size(), 1);
        assertEquals(tasks.findTasksByKeyword("bye").get(0).getDescription(), "bye");
    }
}
