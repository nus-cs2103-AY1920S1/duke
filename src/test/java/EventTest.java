import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

	@Test
	void checkTime() {
		Event test = new Event("birthday", "2/3/2019");
		assertEquals("2nd of March 2019, ", test.checkTime(test.at));
	}

	@Test
	void testToString() {
		Event test = new Event("birthday", "2/3/2019 my house");
		assertEquals("[E][ ] birthday (at: 2nd of March 2019, my house )", test.toString());
	}
}