import logic.Command;
import logic.CommandParser;
import model.Tasklist;
import model.todo;
import storage.Storage;
import ui.UI;
import ui.UI_CLI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WrongCommandTest {

    @Test
    void execute_wrong_test() {
        UI ui = new UI_CLI();
        Storage storage = new Storage();

        Tasklist expectedTasks = new Tasklist();

        Tasklist actualTasks = new Tasklist();
        actualTasks.add(new todo("Borrow a Book"));

        CommandParser commandParser = new CommandParser(" ");
        Command command = commandParser.parseCommand("This is a invalid command");
        String actualOutput = command.execute(actualTasks, ui, storage);
        String expectedOutput = "OOPS!!! I'm sorry, but I don't know what that means :-(\n";

        assertEquals(actualOutput, expectedOutput);
    }
}
