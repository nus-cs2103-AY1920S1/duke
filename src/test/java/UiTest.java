import jermi.component.Ui;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void instantiation_instantiateUi_uiObject() {
        Ui ui = new Ui();
        assertTrue(ui instanceof Ui);
    }

    @Test
    public void echo_noString_onlyBorders() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.echo();
        String border = "    ____________________________________________________________\n";
        assertEquals(border + border + "\n", outContent.toString());

        System.setOut(originalOut);
    }

    @Test
    public void echo_emptyString_singleBlankLineBetweenBorders() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        final PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        Ui ui = new Ui();
        ui.echo("");
        String border = "    ____________________________________________________________\n";
        assertEquals(border + "     \n" + border + "\n", outContent.toString());

        System.setOut(originalOut);
    }
}
