package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import duke.exception.DukeException;
import duke.exception.DuplicateTaskDukeException;
import duke.exception.InvalidTaskDukeException;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

class TaskListTest {
    @Test
    void testTaskList_CreateOneElement() throws DuplicateTaskDukeException {
        TaskList tasks = new TaskList();
        tasks.addTask(new TaskStub());
        assertEquals(1, tasks.size());
    }

    @Test
    void testTaskList_CreateAndDeleteOneElement() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.addTask(new TaskStub());
        tasks.deleteTask(0);
        assertEquals(0, tasks.size());
    }

    @Test
    void testTaskList_DeleteInvalidElement() {
        TaskList tasks = new TaskList();
        assertThrows(InvalidTaskDukeException.class, () -> tasks.deleteTask(0));
    }

    @Test
    void testTaskList_NoElementAtStart()  {
        TaskList tasks = new TaskList();
        assertEquals(0, tasks.size());
    }

    @Test
    void testTaskList_AddTodo() throws DukeException {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("Test 1", false));
        assertEquals("Test 1", tasks.getTask(0).getDescription());
    }

    @Test
    void testTaskList_GetInvalidElement() {
        TaskList tasks = new TaskList();
        assertThrows(InvalidTaskDukeException.class, () -> tasks.getTask(0));
    }

    @Test
    void testTaskList_FindTasksByKeyword() throws DuplicateTaskDukeException {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("hi", false));
        tasks.addTask(new Todo("bye", false));
        assertEquals(1, tasks.findTasksByKeyword("bye").size());
        assertEquals("bye", tasks.findTasksByKeyword("bye").get(0).getDescription());
    }

    @Test
    void testTaskList_DuplicateTask() throws DuplicateTaskDukeException {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("hi", false));
        assertThrows(DuplicateTaskDukeException.class,
            () -> tasks.addTask(new Todo("hi", false)));
    }
}
