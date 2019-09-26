import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.execution.Storage;

public class StorageTest {

    @Test
    public void countLinesTest() throws IOException {
        assertEquals(1, Storage.countLines("test.txt"));
        String file = "test1.txt";
        Storage store = new Storage(file);
        store.addToFile(file, "hello\n yellow\n mellow");
        assertEquals(3, Storage.countLines("test1.txt"));
    }
}