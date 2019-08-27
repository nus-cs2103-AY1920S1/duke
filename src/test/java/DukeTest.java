import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import slave.command.*;
import slave.elements.Date;
import slave.elements.Parser;
import slave.exception.*;


public class DukeTest {

    @Test
    void testDate() throws DukeException {
        Date date = new Date("1/12/2000","0033");
        assertEquals("1st of December 2000, 12.33am", date.convertToString());
    }

    @Test
    void testCommands() throws DukeException {
        String command1 = "list";
        String command2 = "delete 1";
        String command3 = "bye";
        String command4 = "help";
        String command5 = "clear";
        String command6 = "todo Test";
        String command7 = "event Test /at Date";
        String command8 = "deadline Test /by Date";
        String command9 = "done 1";
        assertTrue(Parser.parse(command1) instanceof ListCommand);
        assertTrue(Parser.parse(command2) instanceof DeleteCommand);
        assertTrue(Parser.parse(command3) instanceof ExitCommand);
        assertTrue(Parser.parse(command4) instanceof HelpCommand);
        assertTrue(Parser.parse(command5) instanceof ClearCommand);
        assertTrue(Parser.parse(command6) instanceof AddToDoCommand);
        assertTrue(Parser.parse(command7) instanceof AddEventCommand);
        assertTrue(Parser.parse(command8) instanceof AddDeadlineCommand);
        assertTrue(Parser.parse(command9) instanceof DoneCommand);
    }

    @Test
    void testExceptions() throws DukeException {
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
            c1 = true;
        } catch (MissingDescriptionException e1) {
        }
        try {
            Parser.parse(command2);
            c2 = true;
        } catch (MissingDescriptionException e2) {
        }
        try {
            Parser.parse(command3);
            c3 = true;
        } catch (MissingDescriptionException e3) {
        }
        try {
            Parser.parse(command4);
            c4 = true;
        } catch (MissingTaskException e4) {
        }
        try {
            Parser.parse(command5);
            c5 = true;
        } catch (MissingTaskException e5) {
        }
        try {
            Parser.parse(command6);
            c6 = true;
        } catch (MissingDateException e6) {
        }
        try {
            Parser.parse(command7);
            c7 = true;
        } catch (MissingDateException e7) {
        }
        try {
            Parser.parse(command8);
            c8 = true;
        } catch (InvalidDateException e8 ) {
        }

        assertFalse(c1);
        assertFalse(c2);
        assertFalse(c3);
        assertFalse(c4);
        assertFalse(c5);
        assertFalse(c6);
        assertFalse(c7);
        assertFalse(c8);


    }

}