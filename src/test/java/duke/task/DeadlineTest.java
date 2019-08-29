package duke.task;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    @Test
    void deadlineCreation_normalInput_outputMatches() {
        Task task = null;
        try {
            task = new Deadline("submit assignment", "29/08/2019 2359");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertEquals(task.toString(), "[D][\u2718] submit assignment (by: 29/08/2019 2359)");
    }

    @Test
    void checkDateFormat_wrongDateFormat_exceptionThrown() {
        Task task = null;
        try {
            task = new Deadline("submit quiz", "20/09/2019");
        } catch (ParseException e) {
            assertEquals(e.getMessage(), "Unparseable date: \"20/09/2019\"");
        }
    }

    @Test
    void checkFileStringFormat_correctFormat_success() {
        Task task = null;
        try {
            task = new Deadline("submit quiz", "20/09/2019 2359");
            assertEquals("D | 0 | submit quiz | 20/09/2019 2359", task.getFileStringFormat());
        } catch (ParseException ignored) {
        }
    }
}
