import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class DukeTest {

    @Test
    public void testFileLoad() {
        assertDoesNotThrow(() -> new Duke("Invalid Path"));
    }
}
