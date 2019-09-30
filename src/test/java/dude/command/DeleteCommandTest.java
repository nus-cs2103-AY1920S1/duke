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

public class DeleteCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Test
    public void executeDelete_noTaskNumber_exceptionThrown() {
        try {
            System.setOut(new PrintStream(outContent));
            new DeleteCommand(new String[]{"delete"})
                    .execute(new TaskList(), new Ui(null, null), new Storage("", null));
            fail();
        } catch (DudeException de) {
            String correctExpected = ":( OOPS!!! Please specify number of a single task to delete.\n";
            assertEquals(correctExpected, de.getMessage());
        }
    }
}
