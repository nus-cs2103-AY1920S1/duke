package duke.command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByeCommandTest {
    @Test
    public void test() {
        ByeCommand byeCommand = new ByeCommand();
        assertEquals(byeCommand.isRunning(), false);
    }
}
