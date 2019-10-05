import duke.ui.MessageHandler;
import duke.utilities.Storage;
import duke.task.TaskList;
import duke.command.CreateEventCommand;
import duke.exception.DukeException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class CreateEventCommandTest {
    private static String filePath = "./dukeTest.txt";
    private static Storage storage = new Storage(filePath);
    private static TaskList tasks = new TaskList();
    private static MessageHandler messageHandler = new MessageHandler(tasks, storage);


    @BeforeEach
    public void emptyFile() {
        try {
            PrintWriter pw = new PrintWriter(filePath);
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void executeCreateEventCommand() {
        String expectedSubString1 = "Got it. I've added this task:";
        String expectedSubString2 = "[Event][N] Project Meeting (at: 28/08/2019 1600 - 28/08/2019 1800)";
        String expectedSubString3 = "Now you have 1 task in the list";

        String actual = "";

        CreateEventCommand command = new CreateEventCommand("Project Meeting /at 28/08/2019 1600 - 28/08/2019 1800");
        try {
            actual = command.execute(tasks, messageHandler, storage);
        } catch (DukeException e) {
            System.out.println("execute create event command test should pass, but it didn't because " + e.getMessage());
            fail();
        }

        assertTrue(actual.contains(expectedSubString1));
        assertTrue(actual.contains(expectedSubString2));
        assertTrue(actual.contains(expectedSubString3));
    }
}
