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
        } catch (Exception e) {
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
        } catch (Exception e) {
            // Pass
        }
    }
}