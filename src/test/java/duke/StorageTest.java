package duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StorageTest {
    private static final String location = "StorageTest.txt";
    private static final List<String> data = List
            .of("This is a test file to test Storage.", "You can safely delete it.");

    @Test
    void testStoreAndLoad() throws IOException {
        new Storage(location).store(data);
        List<String> loadedData = new Storage(location).load();
        assertEquals(data, loadedData);
        assertTrue(Files.deleteIfExists(Paths.get(location)));
    }
}
