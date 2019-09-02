import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

	@Test
	void checkTime() {
		Deadline test = new Deadline("homework", "28/8/2019");
		assertEquals("28th of August 2019, ", test.checkTime(test.by));
	}

	@Test
	void testToString() {
		Deadline deadline = new Deadline("homework", "28/8/2019");
		assertEquals("[D][ ] homework (by: 28th of August 2019, )", deadline.toString());
	}
}