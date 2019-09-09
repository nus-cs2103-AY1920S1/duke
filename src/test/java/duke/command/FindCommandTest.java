package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class FindCommandTest {

    @Test
    public void testIsExitValue() {
        assertFalse(new FindCommand("", null, null).isExit());
    }
}
