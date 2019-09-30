package duke.command;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ByeCommandTest {
    @Test
    void run_normalInput_success() {
        List<String> message = new ByeCommand().run(new String[]{"bye"});
        assertEquals(List.of("Bye. Hope to see you again soon!"), message);
    }

    @Test
    void isExit_none_trueReturned() {
        assertTrue(new ByeCommand().isExit());
    }
}
