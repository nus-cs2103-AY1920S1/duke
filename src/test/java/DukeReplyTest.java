import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeReplyTest {
    @Test
    public void testBooleanMatching() {
        DukeReply dukeReply = new DukeReply(true, false, false, "test");
        assertEquals(true, dukeReply.shouldExitLoop);
        assertEquals(false, dukeReply.shouldSaveTaskList);
    }
}