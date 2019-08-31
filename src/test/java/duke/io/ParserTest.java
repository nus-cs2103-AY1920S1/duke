package duke.io;

import duke.command.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void getIndex() {
        assertEquals(2, Parser.getIndex("number 2", 4));
    }

    @Test
    void getDetails() {
        assertEquals("tonight", Parser.getDetails("sleep /by tonight")[1]);
    }
}