package duke.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {
    private Ui ui;
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayOutputStream testOut;

    @BeforeEach
    void setUpStreams() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(systemOut);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @Test
    void readCommand_withTrailingSpaces() {
        String data = "Test   ";
        ByteArrayInputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        ui = new Ui();
        assertEquals("Test", ui.readCommand());
        System.setIn(systemIn);
    }

    @Test
    void showLine() {
        ui = new Ui();
        ui.showLine();
        assertEquals(System.lineSeparator(), getOutput());
    }
}
