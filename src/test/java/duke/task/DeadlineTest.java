package duke.task;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for Deadline class.
 */
public class DeadlineTest {

    /**
     * Create a deadline object with correct input and format.
     *
     * @result A deadline object is created successfully.
     */
    @Test
    public void deadlineCreation_normalInput_outputMatches() {
        Task task = null;
        try {
            task = new Deadline("submit assignment", "29/08/2019 2359");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertEquals(task.toString(), "[D][\u2718] submit assignment (by: 29/08/2019 2359)");
    }

    /**
     * Create a deadline object with wrong date format.
     *
     * @result An exception will be thrown.
     */
    @Test
    public void checkDateFormat_wrongDateFormat_exceptionThrown() {
        Task task = null;
        try {
            task = new Deadline("submit quiz", "20/09/2019");
        } catch (ParseException e) {
            assertEquals(e.getMessage(), "Unparseable date: \"20/09/2019\"");
        }
    }

    /**
     * Check the deadline object file string format.
     *
     * @result The format matches correctly.
     */
    @Test
    public void checkFileStringFormat_correctFormat_success() {
        Task task = null;
        try {
            task = new Deadline("submit quiz", "20/09/2019 2359");
            assertEquals("D | 0 | submit quiz | 20/09/2019 2359", task.getFileStringFormat());
        } catch (ParseException ignored) {
        }
    }
}
