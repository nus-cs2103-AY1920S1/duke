package duke.task;

import duke.task.tasks.Deadline;
import duke.task.tasks.DoAfter;
import duke.task.tasks.Event;
import duke.task.tasks.ToDo;
import error.storage.StorageException;
import error.task.TaskRepoException;
import org.junit.jupiter.api.Test;
import storage.InMemStorage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultTaskRepoTest {

    private List<Task> generateMockTasks() {
        List<Task> mockTasks = new ArrayList<>();
        ToDo mockTaskA = new ToDo("hello", false, false);
        Event mockTaskB = new Event("hello", LocalDateTime.now(), false, false);
        Deadline mockTaskC = new Deadline("hello", LocalDateTime.now(), false, false);
        mockTasks.add(mockTaskA);
        mockTasks.add(mockTaskB);
        mockTasks.add(mockTaskC);

        return mockTasks;
    }

    @Test
    void testGetCurrentTasks() throws StorageException, TaskRepoException {
        InMemStorage storage = new InMemStorage();
        DefaultTaskRepo repo = new DefaultTaskRepo(storage);
        List<Task> mockTasks = this.generateMockTasks();

        storage.writeTasks(mockTasks);
        List<Task> returnedTasks = repo.getCurrentTasks();

        for (int i = 0; i < mockTasks.size(); i++) {
            assertEquals(mockTasks.get(i), returnedTasks.get(i));
        }
    }

    @Test
    void testGetCurrentTasksCount() throws StorageException, TaskRepoException {
        InMemStorage storage = new InMemStorage();
        DefaultTaskRepo repo = new DefaultTaskRepo(storage);
        List<Task> mockTasks = this.generateMockTasks();

        storage.writeTasks(mockTasks);

        assertEquals(mockTasks.size(), repo.getCurrentTasksCount());
    }


    @Test
    void setNewTasks() throws TaskRepoException {
        InMemStorage storage = new InMemStorage();
        DefaultTaskRepo repo = new DefaultTaskRepo(storage);
        List<Task> mockTasks = this.generateMockTasks();

        repo.setNewTasks(mockTasks);
        List<Task> returnedTasks = repo.getCurrentTasks();

        for (int i = 0; i < mockTasks.size(); i++) {
            assertEquals(mockTasks.get(i), returnedTasks.get(i));
        }
    }

    @Test
    void deleteALlTasks() throws StorageException, TaskRepoException {
        InMemStorage storage = new InMemStorage();
        DefaultTaskRepo repo = new DefaultTaskRepo(storage);
        List<Task> mockTasks = this.generateMockTasks();

        storage.writeTasks(mockTasks);
        repo.deleteAllTasks();

        assertEquals(repo.getCurrentTasks().size(), 0);
    }

    @Test
    void getTaskFromListIndex() throws StorageException, TaskRepoException {
        InMemStorage storage = new InMemStorage();
        DefaultTaskRepo repo = new DefaultTaskRepo(storage);
        List<Task> mockTasks = this.generateMockTasks();

        storage.writeTasks(mockTasks);

        List<Task> returnedTasks = repo.getCurrentTasks();
        assertEquals(returnedTasks.get(0), repo.getTaskFromListIndex(0));
        assertEquals(returnedTasks.get(0), repo.getTaskFromListIndex(1));
        assertEquals(returnedTasks.get(0), repo.getTaskFromListIndex(2));
    }

    @Test
    void searchTasks() throws StorageException, TaskRepoException {
        InMemStorage storage = new InMemStorage();
        DefaultTaskRepo repo = new DefaultTaskRepo(storage);
        List<Task> mockTasks = new ArrayList<>();

        storage.writeTasks(mockTasks);

        mockTasks.add(new ToDo("A", false, false));
        mockTasks.add(new Event("b", LocalDateTime.now(), false,false));
        mockTasks.add(new Deadline("aa23", LocalDateTime.now(), false, false));
        mockTasks.add(new DoAfter("Aloha", LocalDateTime.now(), false, false));

        storage.writeTasks(mockTasks);

        List<Task> returnedTasksA = repo.searchTasks("A");
        for (Task task : returnedTasksA) {
            assertTrue(task.getTaskDetails().toLowerCase().contains("a"));
        }

        List<Task> returnedTasksB = repo.searchTasks("12345");
        assertEquals(returnedTasksB.size(), 0);
    }

    @Test
    void deleteTask() throws TaskRepoException {
        InMemStorage storage = new InMemStorage();
        DefaultTaskRepo repo = new DefaultTaskRepo(storage);
        List<Task> mockTasks = new ArrayList<>();

        Task mockTaskA = new Event("b", LocalDateTime.now(), false, false);
        mockTasks.add(new ToDo("A", false, false));
        mockTasks.add(mockTaskA);
        mockTasks.add(new Deadline("aa23", LocalDateTime.now(), false, false));
        mockTasks.add(new DoAfter("Aloha", LocalDateTime.now(), false, false));

        repo.deleteTask(1);
        assertFalse(repo.getCurrentTasks().contains(mockTaskA));
    }

    @Test
    void addTask() throws TaskRepoException {
        InMemStorage storage = new InMemStorage();
        DefaultTaskRepo repo = new DefaultTaskRepo(storage);
        List<Task> mockTasks = this.generateMockTasks();

        Task mockTaskA = new Event("b", LocalDateTime.now(), false, false);
        repo.addTask(mockTaskA);

        assertTrue(repo.getCurrentTasks().contains(mockTaskA));
    }

    @Test
    void updateTask() throws StorageException, TaskRepoException {
        InMemStorage storage = new InMemStorage();
        DefaultTaskRepo repo = new DefaultTaskRepo(storage);
        List<Task> mockTasks = new ArrayList<>();

        Task mockTaskA = new Event("b", LocalDateTime.now(), false, false);
        mockTasks.add(mockTaskA);
        storage.writeTasks(mockTasks);

        Task mockTaskB = new Event("c", LocalDateTime.now(), false, false);
        repo.updateTask(0, mockTaskB);
        assertEquals(mockTaskB, repo.getTaskFromListIndex(0));

        Task mockTaskC = new DoAfter("d", LocalDateTime.now(), false, false);
        assertThrows(TaskRepoException.class, () -> repo.updateTask(0, mockTaskC));
    }

    @Test
    void updateTaskDoneStatus() throws StorageException, TaskRepoException {
        InMemStorage storage = new InMemStorage();
        DefaultTaskRepo repo = new DefaultTaskRepo(storage);
        List<Task> mockTasks = new ArrayList<>();

        Task mockTaskA = new Event("b", LocalDateTime.now(), false, false);
        mockTasks.add(mockTaskA);
        storage.writeTasks(mockTasks);

        repo.updateTaskDoneStatus(0, true);
        assertTrue(repo.getTaskFromListIndex(0).isTaskDone());

        assertThrows(TaskRepoException.class, () -> repo.updateTaskDoneStatus(0, true));
    }
}