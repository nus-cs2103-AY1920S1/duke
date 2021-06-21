import duke.DukeException;
import duke.Ui;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    public void printTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.showLoadingError();
        String expectedOutput  = "    ____________________________________________________________\r\n"
                + "     Cannot load files\r\n"
                + "    ____________________________________________________________\r\n";

        // Do the actual assertion.
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void showDukeExceptionTest() {
        Ui ui = new Ui();
        DukeException ex = new DukeException("dummy ex");
        String expected = "dummy ex";
        String returned = ui.showDukeException(ex);
        assertEquals(expected, returned);
    }
}
