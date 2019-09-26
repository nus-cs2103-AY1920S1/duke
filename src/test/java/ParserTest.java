import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.execution.Parser;

public class ParserTest {

    @Test
    public void formatDateTest() {
        assertEquals("23rd of September 1997, 6:27 am",
                new Parser().formatDate("23/09/1997 0627"));
    }
}
