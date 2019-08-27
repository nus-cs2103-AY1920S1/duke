import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.InputStream;
import java.io.ByteArrayInputStream;

public class DukeManagerTest {
    @Test
    public void initializeDukeTest() {
        try {
            String input = "bye";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            DukeManager dukeManager = new DukeManager();
            dukeManager.initializeDuke();
        } catch (DukeException e) {
            fail(e.getMessage());
        } catch (Exception e) {
            // Not suppose to happen
            fail(e.getMessage());
        }
    }

    @Test
    public void failInitializeDukeTest() {
        try {
            String input = "Fail";
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
            DukeManager dukeManager = new DukeManager();
            dukeManager.initializeDuke();
            fail();
        } catch (DukeException e) {
            // Pass
        } catch (Exception e) {
            // Not suppose to happen
            fail(e.getMessage());
        }
    }
}