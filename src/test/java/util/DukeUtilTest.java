package util;

import duke.util.DukeParser;
import duke.util.ui.DukeUiMessages;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DukeUtilTest {

    private static DukeUiMessages ui;

    @BeforeAll
    public static void beforeAll() {
        ui = new DukeUiMessages();
    }

    @Test
    public void testParser() {
        assertDoesNotThrow(() -> DukeParser.parseCommand("This is an invalid input", ui));
    }
}
