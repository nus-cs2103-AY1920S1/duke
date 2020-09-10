package duke.parser;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void getNameAndDate_ToDo_parsedCorrectly() {
        try {
            assertArrayEquals(new String[]{"assignment 1"},
                Parser.getNameAndDate("todo assignment 1"));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getNameAndDate_ToDo_exceptionThrown() {
        try {
            Parser.getNameAndDate("todo ");
        } catch (DukeException e) {
            String expected = '\u2639' + " OOPS!!! The description of a todo cannot be empty."
                + " Please try again with the following format...\ne.g. todo make bed";
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void getNameAndDate_Deadline_parsedCorrectly() {
        try {
            assertArrayEquals(new String[]{"Make the bed ", " tomorrow"},
                    Parser.getNameAndDate("deadline Make the bed /by tomorrow"));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getNameAndDate_Deadline_exceptionThrown() {
        try {
            Parser.getNameAndDate("deadline ");
        } catch (DukeException e) {
            String expected = '\u2639' + " OOPS!!! The description of a deadline cannot be empty."
                    + " Please try again with the following format...\ne.g. deadline assignment 3 /by 11/12/2019 1500";
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void getNameAndDate_Event_parsedCorrectly() {
        try {
            assertArrayEquals(new String[]{"Gala dinner ", " today - tomorrow"},
                    Parser.getNameAndDate("event Gala dinner /at today - tomorrow"));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void getNameAndDate_Event_exceptionThrown() {
        try {
            Parser.getNameAndDate("event ");
        } catch (DukeException e) {
            String expected = '\u2639' + " OOPS!!! The description of a event cannot be empty."
                    + " Please try again with the following format..."
                    + "\ne.g. event assignment 3 /at 11/12/2019 1500 - 12/12/2019 2000";
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void parseToDoDetails_parsedCorrectly() {
        try {
            assertArrayEquals(new String[]{"Make the bed"},
                    Parser.parseToDoDetails("todo", "Make the bed"));
        } catch (DukeException e) {
            fail();
        }
    }


}
