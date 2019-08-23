package util;

import duke.util.DukeParser;
import duke.util.DukeUi;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DukeUtilTest {

    private static DukeUi ui;

    @BeforeAll
    public static void beforeAll() {
        ui = new DukeUi();
    }

    @Test
    public void testParser() {
        assertDoesNotThrow(() -> DukeParser.parseCommand("This is an invalid input", ui));
    }
}
