package duke.format;

import org.junit.jupiter.api.Test;
import duke.exception.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DateTimeTest {

    @Test
    public void toReformat_correctFormat_success() throws DukeException {
        assertEquals(" 2nd of December 2019, 6pm", new DateTime(" 2/12/2019 1800 ").toReformat());
        assertEquals(" 12th of September 2020, 12:01am", new DateTime(" 12/9/2020 0001 ").toReformat());
        assertEquals(" 23rd of March 2025, 12:55pm", new DateTime("   23/3/2025 1255   ").toReformat());
    }

    @Test
    public void toReformat_missingTime_exceptionThrown() {
        try {
            assertEquals(" 2nd of December 2019, 6pm", new DateTime(" 2/12/2019 ").toReformat());
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! Please provide the date and time in the correct format as follows: DD/MM/YYYY HHMM (eg. 2/12/2019 1800)", e.toString());
        }
    }

    @Test
    public void toReformat_missingYear_exceptionThrown() {
        try {
            assertEquals(" 2nd of December 2019, 6pm", new DateTime(" 2/12 1800 ").toReformat());
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! Please provide the date and time in the correct format as follows: DD/MM/YYYY HHMM (eg. 2/12/2019 1800)", e.toString());
        }
    }

    @Test
    public void toReformat_invalidDay_exceptionThrown() {
        try {
            assertEquals(" 2nd of December 2019, 6pm", new DateTime(" 32/12/2019 1800 ").toReformat());
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! Please provide the date and time in the correct format as follows: DD/MM/YYYY HHMM (eg. 2/12/2019 1800)", e.toString());
        }
    }

    @Test
    public void toReformat_invalidMonth_exceptionThrown() {
        try {
            assertEquals(" 2nd of December 2019, 6pm", new DateTime(" 2/00/2019 1800 ").toReformat());
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! Please provide the date and time in the correct format as follows: DD/MM/YYYY HHMM (eg. 2/12/2019 1800)", e.toString());
        }
    }

    @Test
    public void toReformat_invalidTime_exceptionThrown() {
        try {
            assertEquals(" 2nd of December 2019, 6pm", new DateTime(" 2/12/2019 2400 ").toReformat());
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! Please provide the date and time in the correct format as follows: DD/MM/YYYY HHMM (eg. 2/12/2019 1800)", e.toString());
        }
    }

}
