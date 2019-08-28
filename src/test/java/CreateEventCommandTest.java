import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.UI;
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


public class CreateEventCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private static String filePath = "C:\\Users\\jxken\\Desktop\\Github\\duke\\src\\test\\java\\dukeTest.txt";
    private static Storage storage = new Storage(filePath);
    private static TaskList tasks = new TaskList(storage);
    private static UI ui = new UI(tasks, storage);

    @BeforeEach
    public void setUpStreamsAndEmptyFile() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        try {
            PrintWriter pw = new PrintWriter(filePath);
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void executeCreateEventCommand() {
        String expectedSubString1 = "Got it. I've added this task:";
        String expectedSubString2 = "[Event][✗] Project Meeting (at: 28/08/19 1600 - 28/08/19 1800)";
        String expectedSubString3 = "Now you have 1 task in the list";


        CreateEventCommand command = new CreateEventCommand("Project Meeting /at 28/08/19 1600 - 28/08/19 1800");
        try {
            command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            System.out.println("execute create event command test should pass, but it didn't " + e.getMessage());
        }

        String actual = outContent.toString();

        assertTrue(actual.contains(expectedSubString1));
        assertTrue(actual.contains(expectedSubString2));
        assertTrue(actual.contains(expectedSubString3));
    }
}
