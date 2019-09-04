import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Encapsulates an TodoTest object that contains the operation to test the createTask operation
 * in creating a correct Todo task. It includes storing the Todo object in a temporary list and the local storage.
 */
public class TodoTest {
    @Test
    public void TodoTest() {
        String correct = "Got it. I've added this task:\n" + "    " + "[T][" + "\u2718" + "] Read book\n" + "Now you have 1 task in the list.\n";

        String testFilePath = "/Users/TuanDingWei/Desktop/NUS_Academia" + "/CS2103/Individual_project/Duke/src/test/java/TestTasks.txt";
        Duke duke = new Duke(testFilePath);

        /************************************************
         * Test 1: Correct Test                         *
         ************************************************/
        String data = "todo Read book";
        Parser parser = new Parser(data);
        String userCommand = parser.getUserCommand();
        String due = parser.getDue();
        String taskDescription = parser.getTaskDescription();
        Storage storage = new Storage(testFilePath);
        storage.clear();
        Task.reduceTaskCount();
        Ui ui = new Ui();
        TaskList tasks = new TaskList();
        try {
            assertEquals(correct, duke.createTask(userCommand, due, taskDescription, storage, ui, tasks));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
