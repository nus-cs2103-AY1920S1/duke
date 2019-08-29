import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void parseStoredTask_invalidInput_Exception() {
        try {
            assertEquals("",
                    new Storage("c:/cs2103/ip/data/duke.txt").parseStoredTask("F | Respect"));
        } catch (IOException | DukeException e) {
            assertEquals("OOPS!!! Invalid task.", e.getMessage());
        }
    }
}
