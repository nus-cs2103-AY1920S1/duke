package bot.duke.command.flow;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import bot.duke.storage.Storage;
import bot.duke.ui.Ui;
import duke.task.TaskList;

public class ExitCommandTest {

    @Test
    public void exitCommandTest() {
        assertDoesNotThrow(() -> new ExitCommand()
                .execute(new TaskList(), new Ui(), new Storage("")));
    }

}
