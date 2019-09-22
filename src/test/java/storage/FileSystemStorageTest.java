package storage;

import duke.task.Task;
import duke.task.tasks.Event;
import duke.task.tasks.ToDo;
import error.storage.StorageException;
import error.task.TaskCreationException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemStorageTest {
    private static final String rootTestDirectory = System.getProperty("user.home") + "/DukeTests";

    @Test
    void getInstance() throws StorageException {
        String storagePath = rootTestDirectory + "/Tasks.DAT";
        FileSystemStorage storage = FileSystemStorage.getInstance(storagePath);

        boolean isStorageFileCreated = new File(storagePath).exists();

        assertTrue(isStorageFileCreated);
        assertTrue(deleteDirectory(new File(rootTestDirectory)));
    }

    @Test
    void readAndWriteTasks() throws StorageException, TaskCreationException {
        String storagePath = rootTestDirectory + "/Tasks.DAT";
        FileSystemStorage storage = FileSystemStorage.getInstance(storagePath);

        List<Task> mockTasks = new ArrayList<>();
        ToDo mockTaskA = new ToDo("hello");
        Event mockTaskB = new Event("hello", LocalDateTime.now());
        mockTasks.add(mockTaskA);
        mockTasks.add(mockTaskB);
        storage.writeTasks(mockTasks);

        List<Task> storedTasks = storage.getTasks();
        assertEquals(mockTaskA, storedTasks.get(0));
        assertEquals(mockTaskB, storedTasks.get(1));

        assertTrue(deleteDirectory(new File(rootTestDirectory)));
    }


    private boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }
}