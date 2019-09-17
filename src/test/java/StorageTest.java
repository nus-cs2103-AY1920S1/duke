import duke.storage.Storage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void testGetFilePath() {
        assertEquals("duke/hello.txt", new Storage("duke/hello.txt").getDataFilePath());
    }
}
