package Logic;

import Model.Tasklist;
import Model.todo;
import Storage.Storage;
import UserInterface.UI;
import UserInterface.UI_CLI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {

    @Test
    void execute_delete_test(){
        UI ui = new UI_CLI();
        Storage storage = new Storage();

        Tasklist actualTasks = new Tasklist();
        actualTasks.add(new todo("Borrow a Book"));

        Command command = new DeleteCommand("1");
        String actualOutput = command.execute(actualTasks, ui, storage);
        String expectedOutput = "Noted. I've removed this task:\n" +
                                "[T][x] Borrow a Book\n" +
                                "You now have 0 tasks in this list\n";

        assertEquals(expectedOutput, actualOutput);
    }
}
