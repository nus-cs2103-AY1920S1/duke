package q.main;

import q.error.NoSuchTaskError;
import q.task.Task;
import q.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class TaskListTest {

    @Test
    void isEmpty() {
        assertTrue(new TaskList().isEmpty());
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("test"));
        assertFalse(tasks.isEmpty());
    }

    @Test
    void getSize() {
        assertEquals(0, new TaskList().getSize());
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("test"));
        tasks.addTask(new Todo("test2"));
        assertEquals(2, tasks.getSize());
    }

    @Test
    void doTask_taskExists_success() throws Exception {
        TaskList tasks = new TaskList();
        Task foo = new Todo("test");
        Task bar = new Todo("test2", true);
        assertFalse(tasks.addTask(foo).isDone());
        assertTrue(tasks.doTask(0).isDone());
        tasks.addTask(bar);
        assertTrue(tasks.doTask(1).isDone());
    }

    @Test
    void doTask_taskDoesNotExist_exceptionThrown() {
        TaskList tasks = new TaskList();
        Task foo = new Todo("test");
        Task bar = new Todo("test2", true);
        tasks.addTask(foo);
        try {
            assertEquals(foo, tasks.doTask(1));
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchTaskError);
        }
        tasks.addTask(bar);
        try {
            assertTrue(tasks.doTask(1).isDone());
            assertEquals(foo, tasks.doTask(2));
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchTaskError);
        }
    }

    @Test
    void deleteTask_taskExists_success() throws Exception {
        TaskList tasks = new TaskList();
        Task foo = new Todo("test");
        Task bar = new Todo("test2", true);
        tasks.addTask(foo);
        assertEquals(foo, tasks.deleteTask(0));
        tasks.addTask(bar);
        assertEquals(bar, tasks.deleteTask(0));
        assertTrue(tasks.isEmpty());
    }

    @Test
    void deleteTask_taskDoesNotExist_exceptionThrown() {
        TaskList tasks = new TaskList();
        Task foo = new Todo("test");
        Task bar = new Todo("test2", true);
        tasks.addTask(foo);
        try {
            assertEquals(foo, tasks.deleteTask(1));
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchTaskError);
        }
        tasks.addTask(bar);
        try {
            assertEquals(foo, tasks.doTask(2));
            fail();
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchTaskError);
        }
    }

    @Test
    void addTask() {
        TaskList tasks = new TaskList();
        Task foo = new Todo("test");
        Task bar = new Todo("test2", true);
        assertSame(foo, tasks.addTask(foo));
        assertSame(bar, tasks.addTask(bar));
    }
}