package duke;

import duke.Duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void initializeTestSaveFile() {
        Duke duke = new Duke();
        assertEquals(2, duke.getTodos().size());
        assertEquals(1, duke.getEvents().size());
        assertEquals(1, duke.getDeadlines().size());
    }
}