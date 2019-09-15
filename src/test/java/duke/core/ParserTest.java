package duke.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.core.Parser;
import duke.errors.DukeException;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.commands.DoneCommand;
import duke.commands.DeleteCommand;
import duke.commands.NullCommand;
import duke.commands.AddToDoCommand;
import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;


class ParserTest {

    @Test
    void parseDateTime_validInput_success() {
        try {
            assertEquals("2nd of DECEMBER 1212, 12pm",
                    Parser.parseDateTime("02/12/1212 1200"));
            assertEquals("12th of DECEMBER 1212, 12:13am",
                    Parser.parseDateTime("12/12/1212 0013"));
            assertEquals("23rd of FEBRUARY 2019, 11:12pm",
                    Parser.parseDateTime("23/02/2019 2312"));
            assertEquals("1st of FEBRUARY 2019, 12am",
                    Parser.parseDateTime("01/02/2019 0000"));
        } catch (DukeException e) {
            fail("Should not have thrown exception");
        }
    }

    @Test
    public void parseDateTime_invalidInput_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parseDateTime("1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("12/12/1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("12pm"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("Hello"));
    }

    @Test
    public void parseDateTime_validLookingInvalidNumbers_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parseDateTime("0/12/1212 1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("-1/12/1212 1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("/12/1212 1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("99/12/1212 1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("12/13/1212 1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("12/12/999 1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("12/12/0 1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("12/12/0 00"));
    }



    @Test
    public void parseCommand_missingParameter_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parseCommand("todo "));

        assertThrows(IllegalArgumentException.class, () -> Parser.parseCommand("deadline "));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseCommand("deadline task"));
        assertThrows(DukeException.class, () -> Parser.parseCommand("deadline /by time"));

        assertThrows(IllegalArgumentException.class, () -> Parser.parseCommand("event "));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseCommand("event task "));
        assertThrows(DukeException.class, () -> Parser.parseCommand("event /at time"));

        assertThrows(IllegalArgumentException.class, () -> Parser.parseCommand("done "));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseCommand("delete "));
    }


    @Test
    public void parseCommand_extraArgumentForNoArgumentCommands_success() {
        try {
            assertTrue(Parser.parseCommand("list 1") instanceof ListCommand);
            assertTrue(Parser.parseCommand("bye 1") instanceof ExitCommand);
            assertTrue(Parser.parseCommand("list a") instanceof ListCommand);
            assertTrue(Parser.parseCommand("bye a") instanceof ExitCommand);
        } catch (DukeException ex) {
            fail("Should return a command ignoring the extra arguments");
        }
    }

}