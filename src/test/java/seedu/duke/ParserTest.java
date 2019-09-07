package seedu.duke;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void dateParseTest() throws ParseException, DukeException {
        Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse("3/12/1312 0145");
        assertEquals(date, Parser.dateParse("3/12/1312 0145"));
    }

    @Test
    public void getDateTest1() throws ParseException {
        String[] arr = {"deadline", "art", "festival", "3/12/131", "0145"};  // missing "/by"
        try {
            Parser.getDate(arr);
            fail();
        } catch (MissingTimeStampException e) {
            assertEquals("☹ OOPS!!! Missing timestamp!", e.getMessage());
        }
    }

    @Test
    public void getDateTest2() throws ParseException, DukeException {
        Date date = new SimpleDateFormat("dd/MM/yyyy HHmm").parse("3/12/1332 0145");
        String[] arr = {"deadline", "art", "festival", "/by", "3/12/1332", "0145"};  // missing "/by"
        Parser parser = new Parser();
        parser.getDate(arr);
        assertEquals(date, parser.getDateTime());
        assertEquals(3, parser.getIndexOfByAt());
    }

    @Test
    public void parseTest1() throws ParseException {
        String str = "deadline";  // missing description
        try {
            Ui ui = new Ui();
            TaskList tasks = new TaskList();
            Parser.parse(str, ui, tasks);
            fail();
        } catch (InvalidInputException e) {
            fail();
        } catch (EmptyDescriptionException e) {
            assertEquals("☹ OOPS!!! The description of a " + "deadline"
                    + " command cannot be empty.\n", e.getMessage());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void parseTest2() throws ParseException {
        String str = "blahh";  // invalid input
        try {
            Ui ui = new Ui();
            TaskList tasks = new TaskList();
            Parser.parse(str, ui, tasks);
            fail();
        } catch (InvalidInputException e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n", e.getMessage());
        } catch (EmptyDescriptionException e) {
            fail();
        } catch (DukeException e) {
            fail();
        }
    }

}
