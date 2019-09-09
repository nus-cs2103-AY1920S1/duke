package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class UpdateCommandTest {

    @Test
    public void testIsExitValue() {
        assertFalse(new UpdateCommand("", null, null).isExit());
    }
}
