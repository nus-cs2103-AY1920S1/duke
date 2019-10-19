package duke.model.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for Deadline class.
 */
class DeadlineTest {

    /**
     * Create a deadline object with correct input and format.
     *
     * @result A deadline object is created successfully.
     */
    @Test
    void deadlineCreation_normalInput_outputMatches() {
        Task task = null;
        try {
            task = new Deadline("submit assignment", "29/08/2019 2359");
        } catch (DukeException e) {
            e.printStackTrace();
        }
        assert task != null;
        assertEquals(task.toString(), "[D][x] submit assignment (by: 29/08/2019 2359)");
    }

    /**
     * Create a deadline object with wrong date format.
     *
     * @result An exception will be thrown.
     */
    @Test
    void checkDateFormat_wrongDateFormat_exceptionThrown() {
        Task task = null;
        try {
            task = new Deadline("submit quiz", "20/09/2019");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Unparseable date: \"20/09/2019\"\nPlease use the format: dd/MM/yyyy hhmm");
        }
    }

    /**
     * Check the deadline object file string format.
     *
     * @result The format matches correctly.
     */
    @Test
    void checkFileStringFormat_correctFormat_success() {
        Task task = null;
        try {
            task = new Deadline("submit quiz", "20/09/2019 2359");
            assertEquals("D | 0 | submit quiz | 20/09/2019 2359", task.getFileStringFormat());
        } catch (DukeException e) {
            System.err.println(e.getMessage());
        }
    }
}
