import components.Storage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    private String testPath = "src/test/data/test.txt";

    @Test
    void save() {
        Storage storage = new Storage(testPath);
        storage.save(new ArrayList<>());

    }

    @Test
    void load() {
        //filepath is purposely wrong to test error handling.
        Storage storage = new Storage(testPath);
        assertEquals(0, storage.load().size());
    }
}
