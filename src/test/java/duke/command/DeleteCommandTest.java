package duke.command;

import duke.DukeException;
import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeleteCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private String separationLine = "    ____________________________________________________________";

    @Test
    public void executeDelete_validInput_success() {
        System.setOut(new PrintStream(outContent));
        ArrayList<Task> temp = new ArrayList<>() {{
            add(new Todo("deleteTest", 1));
        }};
        String expected = separationLine + "\n     Noted. I've removed this task:\n       [T][+] deleteTest"
                + "\n     Now you have 0 tasks in the list." + "\n" + separationLine + "\n" + System.lineSeparator();
        new DeleteCommand("", new String[]{"delete", "1"})
                .execute(new TaskList(temp), new Ui(), new Storage(""));
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void executeDelete_noTaskNumber_exceptionThrown() {
        try {
            System.setOut(new PrintStream(outContent));
            new DeleteCommand("", new String[]{"delete"})
                    .execute(new TaskList(), new Ui(), new Storage(""));
            fail();
        } catch (DukeException de) {
            String correctExpected = separationLine
                    + "\n     \u2639 OOPS!!! Please specify number of a single task to delete.\n"
                    + separationLine + "\n";
            assertEquals(correctExpected, de.getMessage());
        }
    }

    @Test
    public void executeDelete_itemNotInt_noOutputOutsideExecute() {
        System.setOut(new PrintStream(outContent));
        new DeleteCommand("", new String[]{"delete", "first item"})
                .execute(new TaskList(), new Ui(), new Storage(""));
        assertEquals("", outContent.toString());
    }

    @Test
    public void executeDelete_intBeyondList_exceptionThrown() {
        try {
            System.setOut(new PrintStream(outContent));
            new DeleteCommand("", new String[]{"delete", "100"})
                    .execute(new TaskList(), new Ui(), new Storage(""));
            fail();
        } catch (DukeException de) {
            String correctExpected = separationLine + "\n     \u2639 OOPS!!! Please specify valid task number.\n"
                    + separationLine + "\n";
            assertEquals(correctExpected, de.getMessage());
        }
    }
}
