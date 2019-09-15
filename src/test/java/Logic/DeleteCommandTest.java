package Logic;

import Model.Tasklist;
import Model.todo;
import Storage.Storage;
import UI.UI;
import UI.UI_CLI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {

    @Test
    void execute_delete_test(){
        UI ui = new UI_CLI();
        Storage storage = new Storage();

        Tasklist expectedTasks = new Tasklist();

        Tasklist actualTasks = new Tasklist();
        actualTasks.add(new todo("Borrow a Book"));

        Command command = new DeleteCommand("1");
        String actualOutput = command.execute(actualTasks, ui, storage);
        String expectedOutput = "Got it. I've added this task:\n" +
                "[T][x] Borrow a Book\n" +
                "Now you have 1 tasks in this list\n";

        //assertEquals(expectedTasks.get(0).getDetails(), actualTasks.get(0).getDetails());
        assertEquals(actualOutput, expectedOutput);
    }
}
