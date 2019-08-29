package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParseDateTimeString() {
        try {
            assertEquals(
                    "2007-12-03T10:15",
                    Parser.parseDateTimeString("03/12/2007, 10:15AM").toString()
            );
        } catch (DukeException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
