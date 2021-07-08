import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Todo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;


public class StorageTest {

    @Test
    public void load_hasExistingFile_success() {
        try {
            Storage storage = new Storage("/Users/stephenchua/duke/src/test/data/test.txt");
            List<Task> expected = new ArrayList<>();
            expected.add(new Todo("read interesting stuff"));
            expected.add(new Todo("build some cool things"));
            for (int i = 0; i < expected.size(); i++) {
                assertEquals(expected.get(i).toString(), storage.load().get(i).toString());
            }
        } catch (DukeException | IOException e) {
            fail();
        }
    }

    //test create a new file if there is no file at that location
    @Test
    public void load_noExistingFile_success() {
        String filePath = "/Users/stephenchua/duke/src/test/data/nonexistentfile.txt";
        Storage storage = new Storage(filePath);
        File f = new File(filePath);
        try {
            storage.load();
            assertTrue(f.exists() && !f.isDirectory());
        } catch (DukeException | IOException e) {
            fail();
        } finally {
            f.delete();
        }
    }
}
