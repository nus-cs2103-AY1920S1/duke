package duke;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private static final String LINE_BREAK = "______________________________________________________________________";
    private static final String HELLO_STRING = "Hello! I'm duke.Duke\nWhat can I do for you?";
    private static final String BYE_STRING = "Bye. Hope to see you again soon!";
    private static final String ERROR_PREPEND = "☹ OOPS!!!";

    public String format(String sequence) {
        String indented = sequence.replaceAll("(?m)^", "\t\t");
        return String.format(
                "\t%s\n%s\n\t%s\n",
                LINE_BREAK,
                indented,
                LINE_BREAK
        );
    }

    @Test
    public void testSay() {
        System.setOut(new PrintStream(outContent));
        Ui ui = new Ui();
        ui.say("yeet");
        assertEquals("yeet\n", outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    public void testGreeting() {
        System.setOut(new PrintStream(outContent));
        Ui ui = new Ui();
        ui.greet();
        assertEquals(HELLO_STRING + "\n", outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    public void testFarewell() {
        System.setOut(new PrintStream(outContent));
        Ui ui = new Ui();
        ui.farewell();
        assertEquals(BYE_STRING + "\n", outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    public void testSayError() {
        System.setOut(new PrintStream(outContent));
        Ui ui = new Ui();
        ui.sayError("The answer is not 42.");
        assertEquals(ERROR_PREPEND + " " + "The answer is not 42.\n", outContent.toString());
        System.setOut(originalOut);
    }
}
