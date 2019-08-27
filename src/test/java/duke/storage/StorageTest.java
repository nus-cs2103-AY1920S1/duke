package duke.storage;
import duke.dukeexception.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    @Test
    public void load_invalidFilePath_exceptionThrown(){
        try {
            new Storage("data/wrongFile.txt").load();
            fail();
        } catch (DukeException de) {
            assertEquals("Error loading from specified file path", de.getMessage());
        }
    }

}
