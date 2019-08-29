
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    public void printTest() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.showLoadingError();
        String expectedOutput  = "    ____________________________________________________________\r\n" +
                "     Cannot load files\r\n" +
                "    ____________________________________________________________\r\n" ;

        // Do the actual assertion.
        assertEquals(expectedOutput, outContent.toString());
    }
}
