import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void EventTest() {
        String correct = "Got it. I've added this task:\n" + "    "
                + "[E][" + "\u2718" + "] Party (at: 02/12/2019 2000 - 0300)\n"
                + "Now you have 1 task in the list.\n";
        String testFilePath = "/Users/TuanDingWei/Desktop/NUS_Academia" + "/CS2103/Individual_project/Duke/src/test/java/TestTasks.txt";
        Duke duke = new Duke(testFilePath);

        /************************************************
         * Test 1: Correct Test                         *
         ************************************************/
        String data = "event Party /at 2/12/2019 2000-0300";
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
