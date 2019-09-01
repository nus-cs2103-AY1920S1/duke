import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import com.util.Printer;

public class DukeTest {

    @Test
    void testPrinterSurroundQuotes() {
        assertEquals("\"hi\"", Printer.surroundQuotes("hi"));
    }

    @Test
    void testPrinterIndentString() {
        assertEquals("    hi\n", Printer.indentString("hi"));
    }
}
