package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testOne() {
        Duke duke = new Duke(Duke.DATA_FILE_TASKS);
        assertEquals(true, true, "the truth");
    }
}
