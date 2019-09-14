package bot.duke.command.add;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

import bot.duke.storage.Storage;
import bot.duke.ui.Ui;
import duke.task.TaskList;

public class AddTodoCommandTest {

    @Test
    public void addTodoCommandTest() {
        assertDoesNotThrow(() -> new AddTodoCommand("Rub Genie Lamp vigorously")
                .execute(new TaskList(), new Ui(), new Storage("")));
    }

}
