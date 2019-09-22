package storage;

import duke.task.Task;
import duke.task.tasks.Event;
import duke.task.tasks.ToDo;
import error.storage.StorageException;
import error.task.TaskCreationException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemStorageTest {
    @Test
    void readAndWriteTasks() throws IllegalAccessException, StorageException, NoSuchFieldException, TaskCreationException {
        InMemStorage storage = new InMemStorage();

        List<Task> mockTasks = new ArrayList<>();
        ToDo mockTaskA = new ToDo("hello");
        Event mockTaskB = new Event("hello", LocalDateTime.now());
        mockTasks.add(mockTaskA);
        mockTasks.add(mockTaskB);

        storage.writeTasks(mockTasks);

        List<Task> storedTasks = storage.getTasks();
        assertEquals(mockTasks, storedTasks);
        assertEquals(mockTaskA, storedTasks.get(0));
        assertEquals(mockTaskB, storedTasks.get(1));
    }

}