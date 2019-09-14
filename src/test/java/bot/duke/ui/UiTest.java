package bot.duke.ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class UiTest {
    @Test
    public void welcomeTest() {
        assertDoesNotThrow(() -> Ui.printWelcomeMsg());
    }
}
