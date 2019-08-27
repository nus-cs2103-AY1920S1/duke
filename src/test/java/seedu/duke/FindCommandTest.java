package seedu.duke;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;

public class FindCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    @Test
    public void test1() {
        FindCommand find = new FindCommand("find e");
        StorageStub st = new StorageStub("dummy");
        TaskList tl = new TaskList(st.load());
        Ui ui = new Ui();

        try {
            find.execute(tl, ui, st);
            assertEquals( "Here are the matching tasks in your list:\r\n"
                    + "1. [T][\u2718] def\r\n\r\n", outContent.toString());
        } catch (Exception e) {
            assertEquals(2, 3);
        }
    }
}
