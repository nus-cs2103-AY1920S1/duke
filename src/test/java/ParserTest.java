import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    public void getMonthFunction_inputIsOne_januaryReturned() throws DukeException {
        assertEquals("January", Parser.getMonth("1"));
    }

    @Test
    public void getMonthFunction_inputIsOutOfBound_exceptionThrown() throws DukeException {
        try {
            assertEquals("February", Parser.getMonth("14"));
        } catch (DukeException e) {
            assertEquals("OOPS!!! The month you inputted is not valid.", e.getMessage());
        }
    }

    @Test
    public void processDeadlineDate_correctDeadlineFormat_deadlineReturned() throws DukeException {
        assertEquals("2nd of December 2019, 6pm", Parser.processDeadlineDate("2/12/2019 1800"));
    }

    @Test
    public void processEventDate_correctDurationFormat_eventTrimeReturned() throws DukeException {
        assertEquals("2nd of December 2019, 6pm-9.30pm", Parser.processEventDate("2/12/2019 1800-2130"));
    }

    


}