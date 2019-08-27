import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ParserTest {
	@Test()
	public void invalidCommand() {
		String input = "a invalid command";
		Throwable e = null;
		try {
			Parser.parse(input);
		} catch (Throwable ex) {
			e = ex;
		}
		assertTrue(e instanceof DukeException);
	}
}
