import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import kappa.command.FindCommand;
import kappa.command.AddDeadlineCommand;
import kappa.command.AddEventCommand;
import kappa.command.ClearCommand;
import kappa.command.DeleteCommand;
import kappa.command.DoneCommand;
import kappa.command.ExitCommand;
import kappa.command.ListCommand;
import kappa.command.AddToDoCommand;
import kappa.command.HelpCommand;

import kappa.elements.DateTime;
import kappa.elements.Parser;

import kappa.exception.KappaException;
import kappa.exception.MissingDescriptionException;
import kappa.exception.MissingTaskException;
import kappa.exception.MissingDateException;
import kappa.exception.InvalidDateException;


/**
 * Test method for Duke.
 */
class DukeTest {

    /**
     * Tests if the date has been converted properly as intended.
     *
     * @throws KappaException For invalid dates.
     */
    @Test
    void testDate() throws KappaException {
        DateTime date = new DateTime("1/12/2000","0033");
        assertEquals("1st of December 2000, 12.33am", date.convertToString());
    }

    /**
     * Tests if the commands return the appropriate command type.
     *
     * @throws KappaException Any exception that is thrown from doing any valid commands.
     */
    @Test
    void testCommands() throws KappaException {

        final String command1 = "list";
        final String command2 = "delete 1";
        final String command3 = "bye";
        final String command4 = "help";
        final String command5 = "clear";
        final String command6 = "todo Test";
        final String command7 = "event Test /at Date";
        final String command8 = "deadline Test /by Date";
        final String command9 = "done 1";
        final String command10 = "find lol";

        assertTrue(Parser.parse(command1) instanceof ListCommand);
        assertTrue(Parser.parse(command2) instanceof DeleteCommand);
        assertTrue(Parser.parse(command3) instanceof ExitCommand);
        assertTrue(Parser.parse(command4) instanceof HelpCommand);
        assertTrue(Parser.parse(command5) instanceof ClearCommand);
        assertTrue(Parser.parse(command6) instanceof AddToDoCommand);
        assertTrue(Parser.parse(command7) instanceof AddEventCommand);
        assertTrue(Parser.parse(command8) instanceof AddDeadlineCommand);
        assertTrue(Parser.parse(command9) instanceof DoneCommand);
        assertTrue(Parser.parse(command10) instanceof FindCommand);
    }

    /**
     * Tests if the correct exceptions are thrown for invalid inputs.
     *
     * @throws KappaException Any exceptions that are thrown from doing any invalid commands.
     */
    @Test
    void testExceptions() throws KappaException {
        String command1 = "todo";
        String command2 = "event";
        String command3 = "deadline";
        String command4 = "delete";
        String command5 = "done";
        String command6 = "event test";
        String command7 = "deadline test";
        String command8 = "event Test /at 10000/1000/11 4444";
        boolean c1 = false;
        boolean c2 = false;
        boolean c3 = false;
        boolean c4 = false;
        boolean c5 = false;
        boolean c6 = false;
        boolean c7 = false;
        boolean c8 = false;
        try {
            Parser.parse(command1);
        } catch (MissingDescriptionException ignored) {
            c1 = true;
        }
        try {
            Parser.parse(command2);
        } catch (MissingDescriptionException ignored) {
            c2 = true;
        }
        try {
            Parser.parse(command3);
        } catch (MissingDescriptionException ignored) {
            c3 = true;
        }
        try {
            Parser.parse(command4);
        } catch (MissingTaskException ignored) {
            c4 = true;
        }
        try {
            Parser.parse(command5);
        } catch (MissingTaskException ignored) {
            c5 = true;
        }
        try {
            Parser.parse(command6);
        } catch (MissingDateException ignored) {
            c6 = true;
        }
        try {
            Parser.parse(command7);
        } catch (MissingDateException ignored) {
            c7 = true;
        }
        try {
            Parser.parse(command8);
        } catch (InvalidDateException ignored) {
            c8 = true;
        }

        assertTrue(c1);
        assertTrue(c2);
        assertTrue(c3);
        assertTrue(c4);
        assertTrue(c5);
        assertTrue(c6);
        assertTrue(c7);
        assertTrue(c8);

    }

}