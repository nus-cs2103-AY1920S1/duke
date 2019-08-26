package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.parser.Parser;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParserTest {
    @Test
    public void test_parse_bye(){
        Command bye = Parser.parse("BYE");
        assertEquals(true, bye instanceof ExitCommand);
    }

    @Test
    public void test_parse_add(){
        Command bye = Parser.parse("aDd");
        assertEquals(true, bye instanceof AddCommand);
    }

    @Test
    public void test_parse_delete(){
        Command bye = Parser.parse("deLetE");
        assertEquals(true, bye instanceof DeleteCommand);
    }

    @Test
    public void test_parse_list(){
        Command bye = Parser.parse("LIst");
        assertEquals(true, bye instanceof ListCommand);
    }

    @Test
    public void test_parse_error(){
        try {
            Command bye = Parser.parse("LALALA");
        } catch (DukeException e) {
            assertEquals("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }
}
