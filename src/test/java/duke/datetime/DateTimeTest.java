package duke.datetime;

import duke.exception.InvalidDateTimeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DateTimeTest {
    @Test
    public void test_newDateTime_exceptionThrown_invalidDate() {
        try {
            DateTime datetime = new DateTime("a/a/a 1700");
            fail();
        } catch (InvalidDateTimeException e) {
            assertEquals("Invalid Date", e.getMessage());
        }
    }

    @Test
    public void test_newDateTime_exceptionThrown_invalidTime() {
        try {
            DateTime datetime = new DateTime("1/1/2015 abcd");
            fail();
        } catch (InvalidDateTimeException e) {
            assertEquals("Invalid Time", e.getMessage());
        }
    }

    @Test
    public void test_newDateTime_exceptionThrown_invalidFormatOne() {
        try {
            DateTime datetime = new DateTime("1/1/2015");
            fail();
        } catch (InvalidDateTimeException e) {
            assertEquals("Invalid DateTime", e.getMessage());
        }
    }

    @Test
    public void test_newDateTime_exceptionThrown_invalidFormatTwo() {
        try {
            DateTime datetime = new DateTime("1700");
            fail();
        } catch (InvalidDateTimeException e) {
            assertEquals("Invalid DateTime", e.getMessage());
        }
    }

    @Test
    public void test_dateTimeToString() {
        try {
            DateTime datetime = new DateTime("1/1/2000 1730");
            assertEquals("1st of January 2000, 5:30pm", datetime.toString());;
        } catch (InvalidDateTimeException e) {
            fail();
        }
    }
}
