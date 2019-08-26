import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeReplyTest {
    @Test
    public void testBooleanMatching() {
        DukeReply dukeReply = new DukeReply(true, false, "test");
        assertEquals(true, dukeReply.shouldExitLoop);
        assertEquals(false, dukeReply.shouldSave);
    }
}