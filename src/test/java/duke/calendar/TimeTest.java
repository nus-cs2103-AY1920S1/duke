package duke.calendar;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class TimeTest {

	@Test
	public void processTime_noExceptionThrown() throws DukeException {
		Time time = new Time("0000");
		assertEquals("12am", time.toString());
	}

	@Test
	public void processDate_invalidMinute_exceptionThrown() throws DukeException {
		try {
			Time date = new Time("1861");
			fail();
		} catch (DukeException exception) {
			assertEquals("\u2639 OOPS!!! Please specify a valid time. " +
					"Also ensure that you have specified the time in 24-hour clock convention E.g. 1800 ",
							exception.getMessage());
		}
	}

	@Test
	public void processDate_invalidHour_exceptionThrown() throws DukeException {
		try {
			Time date = new Time("2540");
			fail();
		} catch (DukeException exception) {
			assertEquals("\u2639 OOPS!!! Please specify a valid time. " +
							"Also ensure that you have specified the time in 24-hour clock convention E.g. 1800 ",
					exception.getMessage());
		}
	}

}
