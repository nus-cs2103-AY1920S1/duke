package duke.calendar;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class DateTest {

	@Test
	public void processDate_noExceptionThrown() throws DukeException {
		Date date = new Date("06/05/2019");
		assertEquals("6th of MAY 2019", date.toString());
	}

	@Test
	public void processDate_invalidMonth_exceptionThrown() throws DukeException {
		try {
			Date date = new Date("06/14/2019");
			fail();
		} catch (DukeException exception) {
			assertEquals("\u2639 OOPS!!! Please specify a valid month.", exception.getMessage());
		}
	}

	@Test
	public void processDate_invalidDay_exceptionThrown() {
		try {
			Date date = new Date("31/11/2019");
			fail();
		} catch (DukeException exception) {
			assertEquals("\u2639 OOPS!!! Please specify a valid day.", exception.getMessage());
		}
	}

	@Test
	public void processDate_invalidFormat_exceptionThrown() {
		try {
			Date date = new Date("11/2019");
			fail();
		} catch (DukeException exception) {
			assertEquals("\u2639 OOPS!!! Please specify a date in the form day/month/year. E.g. 2/12/2019",
					exception.getMessage());
		}
	}
}
