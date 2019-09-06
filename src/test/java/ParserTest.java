import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parsertest() {
        String test = "20/12/2019 1845";
        String output = Parser.getDateTime(test);

        assertEquals("20th of December 2019, 6.45PM", output);
    }
}