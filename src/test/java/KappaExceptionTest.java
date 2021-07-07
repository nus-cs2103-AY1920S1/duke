import kappa.elements.Parser;

import kappa.exception.MissingDescriptionException;
import kappa.exception.MissingTaskException;
import kappa.exception.KappaException;
import kappa.exception.MissingDateException;
import kappa.exception.InvalidDateException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for KappaException.
 */
class KappaExceptionTest {

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
