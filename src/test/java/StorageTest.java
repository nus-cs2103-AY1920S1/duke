import duke.Storage;
import duke.exception.InvalidTaskException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    private final String TEST_DIRECTORY = "./data/test.txt";
    private final String FAIL_DIRECORY = "../~.././...../~~~/";

    @Test
    public void load_directoryNotAccessible_exceptionThrown() {
        Storage storage = new Storage();
        try {
            // Nonsensical directory
            storage.load(FAIL_DIRECORY);
        } catch (InvalidTaskException e) {
            return;
        }
        fail("Unreachable directory accepted without exception thrown");
    }

//    @Test
//    public void load_saveDoesNotExist_newFileCreatedAtInputDirectory() throws InvalidTaskException {
//        Storage storage = new Storage();
//        storage.load(TEST_DIRECTORY);
//        if (Files.notExists(Paths.get(TEST_DIRECTORY))) {
//            fail("Unable to create save file");
//        }
//        File file = new File(TEST_DIRECTORY);
//        file.delete();
//    }
// TODO
//    public load_storageFileCorrupt_exceptionThrown() {
//
//    }
//
//    public save_directoryNotAccessible_exceptionThrown(){
//
//    }
}
