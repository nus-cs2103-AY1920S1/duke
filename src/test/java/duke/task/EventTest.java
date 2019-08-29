package duke.task;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    @Test
    void eventCreation_normalInput_outputMatches() {
        Task task = null;
        try {
            task = new Event("party", "29/08/2019 2200");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertEquals(task.toString(), "[E][\u2718] party (at: 29/08/2019 2200)");
    }

    @Test
    void checkDateFormat_wrongDateFormat_exceptionThrown() {
        Task task = null;
        try {
            task = new Event("event", "20/09/2019");
        } catch (ParseException e) {
            assertEquals(e.getMessage(), "Unparseable date: \"20/09/2019\"");
        }
    }

    @Test
    void checkFileStringFormat_correctFormat_success() {
        Task task = null;
        try {
            task = new Event("an event", "12/10/2020 0900");
            assertEquals("E | 0 | an event | 12/10/2020 0900", task.getFileStringFormat());
        } catch (ParseException ignored) {
        }
    }
}
