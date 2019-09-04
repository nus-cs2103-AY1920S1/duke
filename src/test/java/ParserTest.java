import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author bakwxh
 * @version 0.1
 */
class ParserTest {
	/**
	 * Parser Test.
	 */
	@Test
	void test() {
		Parser parser = new Parser();
		String output = parser.getCommand("todo die");
		assertEquals("todo", output);
	}

}
