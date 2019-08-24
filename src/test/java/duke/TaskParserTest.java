package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.exception.DukeException;
import duke.exception.InvalidInputDukeException;
import duke.exception.UnknownCommandDukeException;
import duke.parser.TaskParser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.jupiter.api.Test;

class TaskParserTest {
    @Test
    void testTodo_ValidInput() throws DukeException {
        Task task = TaskParser.parse("todo do homework");
        assertTrue(task instanceof Todo);
        assertEquals(task.getDescription(), "do homework");
        assertEquals(task.getType(), "T");
    }

    @Test
    void testDeadline_ValidInput() throws DukeException, ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy HHmm");
        Task task = TaskParser.parse("deadline finish project /by 31/12/2019 2359");
        assertTrue(task instanceof Deadline);

        Deadline deadline = (Deadline)task;
        assertEquals(deadline.getDescription(), "finish project");
        assertEquals(deadline.getBy(), formatter.parse("31/12/2019 2359"));
        assertEquals(deadline.getType(), "D");
    }

    @Test
    void testEvent_ValidInput() throws DukeException, ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy HHmm");
        Task task = TaskParser.parse("event open presents /at 25/12/2019 0000");
        assertTrue(task instanceof Event);

        Event event = (Event)task;
        assertEquals(event.getDescription(), "open presents");
        assertEquals(event.getAt(), formatter.parse("25/12/2019 0000"));
        assertEquals(event.getType(), "E");
    }

    @Test
    void testTodo_InvalidInput() {
        assertThrows(InvalidInputDukeException.class, () -> TaskParser.parse("todo"));
        assertThrows(InvalidInputDukeException.class, () -> TaskParser.parse("todo "));
    }

    @Test
    void testDeadline_InvalidInput() {
        assertThrows(InvalidInputDukeException.class, () -> TaskParser.parse("deadline"));
        assertThrows(InvalidInputDukeException.class, () -> TaskParser.parse("deadline "));
        assertThrows(InvalidInputDukeException.class, () -> TaskParser.parse("deadline aa"));
        assertThrows(InvalidInputDukeException.class, () -> TaskParser.parse("deadline /by"));
        assertThrows(InvalidInputDukeException.class, () -> TaskParser.parse("deadline /by abc"));
        assertThrows(InvalidInputDukeException.class, () -> TaskParser.parse("deadline /by 1/1/2019 1200"));
    }

    @Test
    void testEvent_InvalidInput() {
        assertThrows(InvalidInputDukeException.class, () -> TaskParser.parse("event"));
        assertThrows(InvalidInputDukeException.class, () -> TaskParser.parse("event "));
        assertThrows(InvalidInputDukeException.class, () -> TaskParser.parse("event aa"));
        assertThrows(InvalidInputDukeException.class, () -> TaskParser.parse("event /at"));
        assertThrows(InvalidInputDukeException.class, () -> TaskParser.parse("event /at abc"));
        assertThrows(InvalidInputDukeException.class, () -> TaskParser.parse("event /at 1/1/2019 1200"));
    }

    @Test
    void testAdd_Random() {
        assertThrows(UnknownCommandDukeException.class, () -> TaskParser.parse("thiswillfail"));
    }
}
