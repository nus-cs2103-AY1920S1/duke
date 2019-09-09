package bot.duke.command.add;

import bot.duke.storage.Storage;
import bot.duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AddTodoCommandTest {

    @Test
    public void addTodoCommandTest() {
        assertDoesNotThrow(() -> new AddTodoCommand("Rub Genie Lamp vigorously")
                .execute(new TaskList(), new Ui(), new Storage("")));
    }

}
