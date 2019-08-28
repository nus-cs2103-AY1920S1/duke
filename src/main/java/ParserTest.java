import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ParserTest {

	@Test
	void test() {
		Parser parser = new Parser();
		String output = parser.getCommand("todo die");
		assertEquals("todo", output);
	}

}
