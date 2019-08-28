import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Encapsulates an DeadlineTest object that contains the operation to test the createTask operation
 * in creating a correct Deadline task. It includes storing the Deadline object in a temporary list and the local storage.
 */
public class DeadlineTest {
    @Test
    public void deadlineTest() {
        String correct = "Got it. I've added this task:\n" + "    " + "[D][" + "\u2718" + "] Individual Project (by: 03/09/2019 2359)\n" + "Now you have 1 task in the list.\n";

        String testFilePath = "/Users/TuanDingWei/Desktop/NUS_Academia" + "/CS2103/Individual_project/Duke/src/test/java/TestTasks.txt";
        Duke duke = new Duke(testFilePath);

        /************************************************
         * Test 1: Correct Test                         *
         ************************************************/
        String data = "deadline Individual Project /by 03/09/2019 2359";
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