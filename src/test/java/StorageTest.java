import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {

    @Test
    public void testStorageLoad() {
        Storage dummy = new Storage("data/dummy.txt");
        try {
            assertEquals(new ArrayList<Task>(), dummy.load());
            fail();
        } catch (DukeException e) {
            assertEquals("File does not exist", e.getMessage());
        }
    }
}
