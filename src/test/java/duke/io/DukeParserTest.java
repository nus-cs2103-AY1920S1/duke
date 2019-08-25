import org.junit.jupiter.api.Test;
import duke.io.Parser;
import duke.DukeException;
import duke.command.*;

import static org.junit.jupiter.api.Assertions.*;

public class DukeParserTest {

    @Test
    public void parseDateTime_validInput_success() {
        try {
            assertEquals("12th of DECEMBER 1212, 12:12pm", Parser.parseDateTime("12/12/1212 1212"));
            assertEquals("12th of DECEMBER 1212, 12:12am", Parser.parseDateTime("12/12/1212 0012"));
            assertEquals("23rd of JANUARY 2019, 11pm", Parser.parseDateTime("23/01/2019 2300"));
        } catch (DukeException e) {
            fail("Should not have thrown exception");
        }
    }
    @Test
    public void parseDateTime_invalidInput_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parseDateTime("1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("12/12/1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("12pm"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("never"));
    }

    @Test
    public void parseDateTime_validLookingInvalidInput_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parseDateTime("12/00/1212 1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("12/13/1212 1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("99/12/1212 1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("00/12/1212 1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("12/12/0000 1212"));
    }

    @Test
    public void parseAsCommand_missingParameter_exceptionThrown() {
        //task commands
        assertThrows(DukeMissingParameterException.class, () -> Parser.parseAsCommand("todo "));

        assertThrows(DukeMissingParameterException.class, () -> Parser.parseAsCommand("deadline "));
        assertThrows(DukeMissingParameterException.class, () -> Parser.parseAsCommand("deadline task"));
        assertThrows(DukeMissingParameterException.class, () -> Parser.parseAsCommand("deadline /by time"));

        assertThrows(DukeMissingParameterException.class, () -> Parser.parseAsCommand("event "));
        assertThrows(DukeMissingParameterException.class, () -> Parser.parseAsCommand("event task "));
        assertThrows(DukeMissingParameterException.class, () -> Parser.parseAsCommand("event /at time"));

        //other commands
        assertThrows(DukeMissingParameterException.class, () -> Parser.parseAsCommand("done "));
        assertThrows(DukeMissingParameterException.class, () -> Parser.parseAsCommand("delete "));
    }

    @Test
    public void parseAsCommand_redundantArgumentForNoArgumentCommands_success() {
        try {
            assertTrue(Parser.parseAsCommand("list 1") instanceof ShowListCommand);
            assertTrue(Parser.parseAsCommand("bye 1") instanceof ExitCommand);
            assertTrue(Parser.parseAsCommand("list a") instanceof ShowListCommand);
            assertTrue(Parser.parseAsCommand("bye a") instanceof ExitCommand);
        } catch (DukeException ex) {
            fail("Should return a command ignoring the redundant arguments");
        }
    }

    @Test
    public void parseAsCommand_nonParseableTimeForDeadlineOrEvent_success() {
        try {
            assertTrue(Parser.parseAsCommand("deadline task /by time") instanceof AddTaskCommand);
            assertTrue(Parser.parseAsCommand("event task /at time") instanceof AddTaskCommand);
        } catch (DukeException ex) {
            fail("Should return without formatting the time argument into dd/MM/yyyy HHmm");
        }
    }
}