import utilities.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void isNumericTest() {
        assertEquals(true, Parser.isNumeric("1"));
    }
}
