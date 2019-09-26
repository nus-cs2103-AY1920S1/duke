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

/**
 * Class to test certain functionality of the Parser class
 */
class ParserTest {

    /**
     * Test that the parseDateTime method can convert into
     * formatted date and time when a valid input is given
     */
    @Test
    void parseDateTime_validInput_success() {
        try {
            assertEquals("2nd of DECEMBER 1212, 12pm",
                    Parser.parseDateTime("02/12/1212 1200"));
            assertEquals("12th of DECEMBER 1212, 12:13am",
                    Parser.parseDateTime("12/12/1212 0013"));
            assertEquals("23rd of FEBRUARY 2019, 11:12pm",
                    Parser.parseDateTime("23/02/2019 2312"));
        } catch (DukeException e) {
            fail("Should not have thrown exception");
        }
    }

    /**
     * Test that the parseDateTime method throws an exception
     * when it tries to parse and format an invalid input
     */
    @Test
    void parseDateTime_invalidInput_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parseDateTime("1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("12/12/1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("12pm"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("Hello"));
    }

    /**
     * Test that the parseDateTime method throws an exception when the input
     * is potentially valid but cannot be formatted to the expected format
     */
    @Test
    void parseDateTime_validLookingInvalidNumbers_exceptionThrown() {
        assertThrows(DukeException.class, () -> Parser.parseDateTime("0/12/1212 1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("-1/12/1212 1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("/12/1212 1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("99/12/1212 1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("12/13/1212 1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("12/12/999 1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("12/12/0 1212"));
        assertThrows(DukeException.class, () -> Parser.parseDateTime("12/12/0 00"));
    }


    /**
     * Test that the parseCommand method throws the expected Exceptions when the it tries to parse a input
     * as a command, but the required arguments are not provided
     */
    @Test
    void parseCommand_missingParameter_exceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parseCommand("todo "));

        assertThrows(IllegalArgumentException.class, () -> Parser.parseCommand("deadline "));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseCommand("deadline task"));
        assertThrows(DukeException.class, () -> Parser.parseCommand("deadline /by time"));

        assertThrows(IllegalArgumentException.class, () -> Parser.parseCommand("event "));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseCommand("event task "));
        assertThrows(DukeException.class, () -> Parser.parseCommand("event /at time"));

        assertThrows(IllegalArgumentException.class, () -> Parser.parseCommand("event task /at "));

        assertThrows(IllegalArgumentException.class, () -> Parser.parseCommand("done "));
        assertThrows(IllegalArgumentException.class, () -> Parser.parseCommand("delete "));
    }

    /**
     * Test that the parseCommand method properly still return an valid AddCommand
     * even when the deadline is not in recognisable dateTime format
     */
    @Test
    void parseCommand_nonParseableTimeForDeadlineOrEvent_success() {
        try {
            assertTrue(Parser.parseCommand("deadline task /by time") instanceof AddDeadlineCommand);
            assertTrue(Parser.parseCommand("event task /at time") instanceof AddEventCommand);
        } catch (DukeException ex) {
            fail("Should return without formatting the time parameter into dd/MM/yyyy HHmm");
        }
    }

    /**
     * Test that the parseCommand method ignores extra input when attempting to parse commands
     * which do not require arguments.
     */
    @Test
    void parseCommand_extraArgumentForNoArgumentCommands_success() {
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