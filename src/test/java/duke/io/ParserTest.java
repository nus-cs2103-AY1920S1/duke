package duke.io;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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