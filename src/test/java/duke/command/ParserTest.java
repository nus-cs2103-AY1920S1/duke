package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ParserTest {

    @Test
    void joinStrings_normalInput_joinedString() {
        assertEquals("read book", Parser.joinStrings(new String[]{"read", "book"}));
    }

    @Test
    void splitByIdentifier_normalInput_splitString() {
        assertArrayEquals(new String[]{"return book", "02/12/2019 1800"},
                Parser.splitByIdentifier(new String[]{"return book", "/by", "02/12/2019 1800"}, "/by"));
    }

}
