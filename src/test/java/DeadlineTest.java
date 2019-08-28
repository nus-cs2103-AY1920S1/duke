import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void deadlineTest() {
        String correct = "Got it. I've added this task:\n" + "    "
                + "[D][" + "\u2718" + "] Individual Project (by: 03/09/2019 2359)\n"
                + "Now you have 1 task in the list.\n";

        String testFilePath = "/Users/TuanDingWei/Desktop/NUS_Academia" +
                "/CS2103/Individual_project/Duke/src/test/java/TestTasks.txt";
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

        /************************************************
         * Test 2: Wrong time format                     *
         ************************************************/
//        String data2 = "deadline Individual Project /by 03/09/2019 5000";
//        Parser parser2 = new Parser(data2);
//        String userCommand2 = parser2.getUserCommand();
//        String due2 = parser2.getDue();
//        String taskDescription2 = parser2.getTaskDescription();
//        Storage storage2 = new Storage(testFilePath);
//        storage.clear();
//        Task.reduceTaskCount();
//        Ui ui2 = new Ui();
//        TaskList tasks2 = new TaskList();
//        try {
//            assertEquals(correct, duke.createTask(userCommand2, due2, taskDescription2, storage2, ui2, tasks2));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}