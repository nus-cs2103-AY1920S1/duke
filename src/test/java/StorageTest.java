import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import java.util.ArrayList;

public class StorageTest {

    @Test
    public void testStorageLoad() {
        Storage dummy = new Storage("data/dummy.txt");
        try {
            assertEquals(new ArrayList<Task>(), dummy.load());
            fail();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            assertEquals("data\\dummy.txt (The system cannot find the file specified)", e.getMessage());
        }
    }
}
