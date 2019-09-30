import commands.DukeException;
import components.Storage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    private String testPath = "test.txt";

    @Test
    void save() throws DukeException {
        Storage storage = new Storage(testPath);
        storage.save(new ArrayList<>());

    }

    @Test
    void load() throws DukeException {
        //filepath is purposely wrong to test error handling.
        Storage storage = new Storage(testPath);
        assertEquals(0, storage.load().size());
    }
}
