package dude.command;

import dude.DudeException;
import dude.helper.Storage;
import dude.helper.Ui;
import dude.task.TaskList;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DoneCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    public void executeDone_noTaskNumber_exceptionThrown() {
        try {
            System.setOut(new PrintStream(outContent));
            new DoneCommand(new String[]{"done"})
                    .execute(new TaskList(), new Ui(null, null), new Storage("", null));
            fail();
        } catch (DudeException de) {
            String correctExpected = ":( OOPS!!! Please specify number of a single task to mark as done.\n";
            assertEquals(correctExpected, de.getMessage());
        }
    }
}
