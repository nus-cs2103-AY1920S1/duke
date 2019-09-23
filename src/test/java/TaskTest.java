import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import task.DukeDate;
import task.Deadline;
import task.Event;
import task.DukeTime;
import task.Todo;

import exceptions.InvalidInputException;
import exceptions.MissingInputException;

public class TaskTest {

    @Test
    public void taskConstructor_success() throws InvalidInputException, MissingInputException {
        assertEquals(new Event(3, TestCase.EVENT_DESCRIPTION, DukeDate.processDate(TestCase.DATE),
                DukeTime.processTime(TestCase.TIME), "E").toString(), TestCase.EVENT_SUCCESSFUL_OUTPUT);
        assertEquals(new Todo(3, TestCase.TODO_DESCRIPTION, "T").toString(), TestCase.TODO_SUCCESSFUL_OUTPUT);
        assertEquals(new Deadline(3, TestCase.DEADLINE_DESCRIPTION, DukeDate.processDate(TestCase.DATE),
                DukeTime.processTime(TestCase.TIME), "D").toString(), TestCase.DEADLINE_SUCCESSFUL_OUTPUT);
    }

    @Test
    public void taskConstructor_throwsException() {
        assertThrows(MissingInputException.class, () ->
                new Event(3, TestCase.DESCRIPTION_FALSE, DukeDate.processDate(TestCase.DATE),
                        DukeTime.processTime(TestCase.TIME), "E"));
        assertThrows(MissingInputException.class, () ->
                new Deadline(3, TestCase.DESCRIPTION_FALSE, DukeDate.processDate(TestCase.DATE),
                        DukeTime.processTime(TestCase.TIME), "D"));
        assertThrows(MissingInputException.class, () ->
                new Todo(3, TestCase.DESCRIPTION_FALSE, "T"));
        assertThrows(MissingInputException.class, () ->
                new Event(3, TestCase.EVENT_DESCRIPTION, null,
                        DukeTime.processTime(TestCase.TIME),"E"));
        assertThrows(MissingInputException.class, () ->
                new Event(3, TestCase.EVENT_DESCRIPTION,
                        DukeDate.processDate(TestCase.DATE), null,"E"));
    }
}
