package Logic;

import Model.Tasklist;
import Model.todo;
import Storage.Storage;
import UserInterface.UI;
import UserInterface.UI_CLI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoCommandTest {

    @Test
    void execute_todo_test(){
        UI ui = new UI_CLI();
        Storage storage = new Storage();

        Tasklist expectedTasks = new Tasklist();
        expectedTasks.add(new todo("Borrow a Book"));

        Tasklist actualTasks = new Tasklist();

        Command command = new TodoCommand("Borrow a Book");
        String actualOutput = command.execute(actualTasks, ui, storage);
        String expectedOutput = "Got it. I've added this task:\n" +
                                "[T][x] Borrow a Book\n" +
                                "Now you have 1 tasks in this list\n";

        assertEquals(expectedTasks.get(0).getDetails(), actualTasks.get(0).getDetails());
        assertEquals(actualOutput, expectedOutput);
    }

}
