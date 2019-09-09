package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ListCommandTest {

    @Test
    public void testIsExitValue() {
        assertFalse(new ListCommand("", null).isExit());
    }
}
