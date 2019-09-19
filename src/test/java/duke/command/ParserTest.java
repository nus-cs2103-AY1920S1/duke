package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ParserTest {

    @Test
    void getType() {
        try {
            assertEquals("event", new Parser("evENT apply for job /at 12/06/2000 1900-2100").getType());
            assertEquals("deadline", new Parser("DEAdline do something /by 23/05/2000 1900").getType());
            assertEquals("todo", new Parser("TodO something").getType());
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test
    void isValid() {
        try {
            assertTrue(new Parser("TOdo well lol").isValid());
            assertTrue(new Parser("event concentrate /at 05/09/2019 0001-2359").isValid());
            assertTrue(new Parser("deadline sleep /by 04/09/2019 2355").isValid());
            assertFalse(new Parser("todos something").isValid());
            assertFalse(new Parser("deadsd svsr   /by 27/07/2080 1900").isValid());
            assertFalse(new Parser("feeling good /at 29/08/2019 1000-1200").isValid());
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
    }
}