package dukebot.command.flow;

import dukebot.storage.Storage;
import dukebot.task.TaskList;
import dukebot.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ExitCommandTest {

    @Test
    public void exitCommandTest() {
        assertDoesNotThrow(() -> new ExitCommand()
                .execute(new TaskList(), new Ui(), new Storage("")));
    }

}
